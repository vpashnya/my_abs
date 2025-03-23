package ru.pvn.levelup.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.DepositOperationDaoImpl;
import ru.pvn.levelup.entities.*;
import ru.pvn.levelup.integration.IntegrationResource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class DepositOperationUtils {

    private ObjectMapper jsonMapper = new ObjectMapper();

    public void addProcent(Deposit deposit, LocalDate currDate) {

        BigDecimal depositRest = DepositUtils.getRest(deposit);
        BigDecimal procent = depositRest.multiply(deposit.getRate()).divide(new BigDecimal(365 * 100), 2, RoundingMode.HALF_UP);

        DepositOperation depOper = new DepositOperation(
                null
                , deposit
                , currDate
                , procent
                , null
                , null
                , "Причисление процентов "
                , DepositOperation.State.NEW
                , null
        );

        DepositOperationDaoImpl.getCurrentDao().save(depOper);

        PayDocument payDocument = new PayDocument(
                null
                , new Account(1, null, null, null, null)
                , new Account(deposit.getAccountExtId(), null, null, null, null)
                , procent
                , null
                , null
                , null
                , depOper.getPurpose()
        );

        execPayDocument(depOper, payDocument);
    }

    public void openDeposit(Deposit deposit) {
        DepositOperation depOper = new DepositOperation(
                null
                , deposit
                , deposit.getDateOpen()
                , deposit.getAmount()
                , null
                , null
                , "Открытие вклада "
                , DepositOperation.State.NEW
                , null
        );

        DepositOperationDaoImpl.getCurrentDao().save(depOper);

        CashOperation cashOper = new CashOperation(
                null
                , null
                , depOper.getDeposit().getAccountNum()
                , depOper.getOperSum()
                , CashOperation.Direction.TO_BANK
                , null
                , null
                , depOper.getPurpose()
        );
        execCashOperation(depOper, cashOper);
    }

    public void closeDepositOperations(Deposit deposit, LocalDate currDate) {

        BigDecimal depositRest = DepositUtils.getRest(deposit);

        DepositOperation depOper = new DepositOperation(
                null
                , deposit
                , currDate
                , depositRest
                , null
                , null
                , "Закрытие вклада"
                , DepositOperation.State.NEW
                , null
        );

        if (deposit.getPayTo() == Deposit.PayTo.IN_BANK) {
            PayDocument payDocument = new PayDocument(
                    null
                    , new Account(deposit.getAccountExtId(), null, null, null, null)
                    , new Account(1, null, null, null, null)
                    , depositRest
                    , null
                    , null
                    , null
                    , "Выплата влкада при закрытии"
            );
            execPayDocument(depOper, payDocument);

        } else {
            CashOperation cashOperation = new CashOperation(
                    null
                    , null
                    , deposit.getAccountNum()
                    , depositRest
                    , CashOperation.Direction.FROM_BANK
                    , null
                    , null
                    , "Выплата влкада при закрытии"
            );
            execCashOperation(depOper, cashOperation);
        }
    }

    public void execPayDocument(DepositOperation depOper, PayDocument payDocument) {
        depOper.setState(DepositOperation.State.SENDED);
        DepositOperationDaoImpl.getCurrentDao().save(depOper);
        try {
            PayDocument execDoc = sendPayDocument2Exec(payDocument);
            depOper.setPayDocId(execDoc.getId());
            if (execDoc.getState() == PayDocument.State.REFUSED) {
                throw new RuntimeException(execDoc.getRefuseReason());
            }
            depOper.setState(DepositOperation.State.EXECUTED);
            DepositOperationDaoImpl.getCurrentDao().save(depOper);
        } catch (RuntimeException e) {
            depOper.setState(DepositOperation.State.REFUSED);
            depOper.setRefuseReason(e.getMessage());
            DepositOperationDaoImpl.getCurrentDao().save(depOper);
            throw e;
        }
    }

    public void execCashOperation(DepositOperation depOper, CashOperation cashOper) {
        depOper.setState(DepositOperation.State.SENDED);
        DepositOperationDaoImpl.getCurrentDao().save(depOper);

        try {
            CashOperation execCashOper = sendCashOperation2Exec(cashOper);
            depOper.setCashOperId(execCashOper.getId());
            if (execCashOper.getState() == CashOperation.State.REFUSED) {
                throw new RuntimeException(execCashOper.getRefuseReason());
            }
            depOper.setState(DepositOperation.State.EXECUTED);
            DepositOperationDaoImpl.getCurrentDao().save(depOper);
        } catch (RuntimeException e) {
            depOper.setRefuseReason(e.getMessage());
            depOper.setState(DepositOperation.State.REFUSED);
            DepositOperationDaoImpl.getCurrentDao().save(depOper);
            throw e;
        }
    }

    @SneakyThrows
    public CashOperation sendCashOperation2Exec(CashOperation cashOperation) {

        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String reqBody = jsonMapper.writeValueAsString(cashOperation);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.CASHWINDOW_CASHOPERATION))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofByteArray(reqBody.getBytes()))
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), CashOperation.class);
    }

    @SneakyThrows
    public PayDocument sendPayDocument2Exec(PayDocument payDocument) {
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String reqBody = jsonMapper.writeValueAsString(payDocument);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_PAYDOC))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofByteArray(reqBody.getBytes()))
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), PayDocument.class);
    }

    public List<DepositOperation> getAllDepositOperation() {
        return DepositOperationDaoImpl.getCurrentDao().getAll();
    }

    public DepositOperation getDepositOperationById(int id) {
        return DepositOperationDaoImpl.getCurrentDao().get(id);
    }

}
