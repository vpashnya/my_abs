package ru.pvn.levelup.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.pvn.levelup.abscore.Constants;
import ru.pvn.levelup.dao.DepositDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.integration.IntegrationResource;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@UtilityClass
public class DepositUtils {

    private ObjectMapper jsonMapper = new ObjectMapper();

    public List<Deposit> getAllDeposits() {
        return DepositDaoImpl.getCurrentDao().getAll();
    }

    public Deposit getDepositById(int id) {
        return DepositDaoImpl.getCurrentDao().get(id);
    }

    @SneakyThrows
    public BigDecimal getRest(Deposit deposit) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(URI.create(IntegrationResource.FINCORE_ACCOUNTREST + "?accnum=" + deposit.getAccountNum()))
                    .headers("Content-Type", "text/plain;charset=UTF-8")
                    .version(HttpClient.Version.HTTP_1_1)
                    .GET()
                    .build();

            HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

            return jsonMapper.readValue(new String(resp.body()), Account.class).getRest();
        } catch (RuntimeException e) {
            return BigDecimal.ZERO;
        }
    }

    public Deposit createDeposit(Map<String, String[]> params) {
        Client client = DepositUtils.getClient(params.get("inn")[0], params.get("name")[0]);
        Account account = DepositUtils.getAccount(Integer.parseInt(params.get("duration")[0]), client);
        LocalDate operDay = DepositUtils.getOperDay();

        Deposit deposit = new Deposit(null
                , client.getId()
                , client.getFullName()
                , account.getId()
                , account.getAccNum()
                , new BigDecimal(params.get("sum")[0])
                , new BigDecimal(params.get("prc")[0].replace("%", " ").trim())
                , Integer.parseInt(params.get("duration")[0])
                , operDay
                , Deposit.Status.NEW
                , switch (params.get("payto")[0]) { case "cash" -> Deposit.PayTo.TO_CASH; default -> Deposit.PayTo.IN_BANK;}
                , null);

        DepositUtils.saveDeposit(deposit);

        try {
            DepositOperationUtils.openDeposit(deposit);
            deposit.setStatus(Deposit.Status.WORK);
        } catch (RuntimeException e) {
            deposit.setStatus(Deposit.Status.REFUSED);
            DepositUtils.saveDeposit(deposit);
            throw e;
        }
        return deposit;
    }

    public void closeDeposit(Deposit deposit, LocalDate currDate) {
        DepositOperationUtils.closeDepositOperations(deposit, currDate);
        deposit.setStatus(Deposit.Status.CLOSED);
        DepositUtils.saveDeposit(deposit);
    }

    @SneakyThrows
    public Client getClient(String inn, String name) {
        Client client = new Client(null, name, inn);
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String reqBody = jsonMapper.writeValueAsString(client);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_CLIENT))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofByteArray(reqBody.getBytes()))
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), Client.class);
    }

    @SneakyThrows
    public Account getAccount(Integer duration, Client client) {
        String accNum = switch (duration) {
            case 1 -> "40817";
            case 3 -> "42301";
            case 7 -> "42302";
            case 14 -> "42303";
            case 28 -> "42304";
            case 45 -> "42305";
            case 61 -> "42307";
            default -> "42309";
        };

        Account account = new Account(null, null, accNum, client, BigDecimal.ZERO);
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String reqBody = jsonMapper.writeValueAsString(account);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_ACCOUNT))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofByteArray(reqBody.getBytes()))
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), Account.class);
    }

    @SneakyThrows
    public LocalDate getOperDay() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_OPERDAY))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        String date = jsonMapper.readValue(resp.body(), String.class);
        LocalDate operDay = LocalDate.parse(date, Constants.INTEGRAION_DATE_FORMAT);

        return operDay;
    }

    public void saveDeposit(Deposit deposit) {
        DepositDaoImpl.getCurrentDao().save(deposit);
    }

}
