package ru.pvn.levelup.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CashOperationDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.entities.PayDocument;
import ru.pvn.levelup.integration.IntegrationResource;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

@UtilityClass
public class CashOperationUtils {
    private final CashOperationDaoImpl currentDao = CashOperationDaoImpl.getCurrentDao();
    private final Random random = new Random();
    private final ObjectMapper jsonMapper = new ObjectMapper();
    {
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<CashOperation> getAllCashOperations() {
        return currentDao.getAll();
    }

    public CashOperation getCashOperationById(int id) {
        return currentDao.get(id);
    }

    public void saveAndExecute(CashOperation cashOperation) {
        cashOperation.setState(CashOperation.State.NEW);
        List<CashPoint> cashPointList = CashPointUtils.getAllCashPoints();
        cashOperation.setCashPoint(cashPointList.get(random.nextInt(cashPointList.size())));

        currentDao.save(cashOperation);

        if ((cashOperation.getSumDoc() == null) || (cashOperation.getSumDoc().equals(BigDecimal.ZERO))) {
            cashOperation.setState(CashOperation.State.REFUSED);
            cashOperation.setRefuseReason("Сумма документа 0");
            currentDao.save(cashOperation);
            return;
        }

        try {
            if (cashOperation.getDirection() == CashOperation.Direction.FROM_BANK) {
                normalizeCashAccountRest(cashOperation.getCashPoint(), cashOperation.getSumDoc());
            }

            PayDocument payDocTo = convertToPayDocument(cashOperation);
            cashOperation.setState(CashOperation.State.SENDED);
            currentDao.save(cashOperation);

            PayDocument payDocFrom = sendPayDocument2FinCore(payDocTo);

            if (payDocFrom.getState() == PayDocument.State.EXECUTED) {
                cashOperation.setState(CashOperation.State.EXECUTED);
            } else {
                cashOperation.setState(CashOperation.State.REFUSED);
                cashOperation.setRefuseReason(payDocFrom.getRefuseReason());
            }
        } catch (Exception e) {
            cashOperation.setState(CashOperation.State.REFUSED);
            cashOperation.setRefuseReason(e.getMessage());
        }
        currentDao.save(cashOperation);
    }

    public PayDocument convertToPayDocument(CashOperation cashOperation) {
        Account debet = new Account();
        Account credit = new Account();

        if (cashOperation.getDirection() == CashOperation.Direction.TO_BANK) {
            debet.setAccNum(cashOperation.getCashPoint().getPointAccountNum());
            credit.setAccNum(cashOperation.getAccountNum());
        } else {
            credit.setAccNum(cashOperation.getCashPoint().getPointAccountNum());
            debet.setAccNum(cashOperation.getAccountNum());
        }

        PayDocument payDocument = new PayDocument(null, debet, credit, cashOperation.getSumDoc(), null, null, null, cashOperation.getPurpose());
        return payDocument;
    }

    @SneakyThrows
    public PayDocument sendPayDocument2FinCore(PayDocument payDocument) {
        String reqBody = jsonMapper.writeValueAsString(payDocument);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_PAYDOCUMENT))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofByteArray(reqBody.getBytes()))
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), PayDocument.class);
    }

    @SneakyThrows
    public void normalizeCashAccountRest(CashPoint cashPoint, BigDecimal needSum) {
        Account account = getAccountByNum(cashPoint.getPointAccountNum());

        BigDecimal docSum = needSum.add(account.getRest());

        if (BigDecimal.ZERO.compareTo(docSum) < 0) {
            PayDocument payDoc = new PayDocument(null
                    , account
                    , new Account(1, null, null, null, null)
                    , docSum
                    , null
                    , null
                    , null
                    , "Подкрепление кассы " + cashPoint.getPointName()
            );
            PayDocument realDoc = sendPayDocument2FinCore(payDoc);
            if (realDoc.getState() == PayDocument.State.REFUSED) {
                throw new RuntimeException("Ошибка подкрепления кассы " + realDoc.getRefuseReason());
            }
        }
    }

    @SneakyThrows
    public Account getAccountByNum(String accountNum) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.FINCORE_ACCOUNTREST + "?accnum=" + accountNum))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<byte[]> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());

        return jsonMapper.readValue(new String(resp.body()), Account.class);
    }

    public Integer getCountOperationsByCashPoint(CashPoint cashPoint) {
        return currentDao.getCountOperationsByCashPoint(cashPoint);
    }
}
