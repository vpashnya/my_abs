package ru.pvn.levelup.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CashOperationDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.entities.PayDocument;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

@UtilityClass
public class CashOperationUtils {
    private CashOperationDaoImpl currentDao = CashOperationDaoImpl.getCurrentDao();
    private Random random = new Random();

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
            PayDocument payDocTo = convertToPayDocument(cashOperation);
            cashOperation.setState(CashOperation.State.SENDED);
            currentDao.save(cashOperation);

            PayDocument payDocFrom = sendPayDocument2FinCore(payDocTo);

            if(payDocFrom.getState() == PayDocument.State.EXECUTED){
                cashOperation.setState(CashOperation.State.EXECUTED);
            }else{
                cashOperation.setState(CashOperation.State.REFUSED);
                cashOperation.setRefuseReason(payDocFrom.getRefuseReason());
            }
        }catch (RuntimeException | IOException | InterruptedException e){
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

        PayDocument payDocument = new PayDocument(null, debet, credit, cashOperation.getSumDoc(), null, null, null);
        return payDocument;
    }

    public PayDocument sendPayDocument2FinCore(PayDocument payDocument) throws IOException, InterruptedException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String sReqBody = jsonMapper.writeValueAsString(payDocument);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/fin_core_war/paydocument"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(sReqBody))
                .build();

        HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("Resp.body : " + resp.body());

        return jsonMapper.readValue(resp.body(), PayDocument.class);
    }

    public Integer getCountOperationsByCashPoint(CashPoint cashPoint) {
        return currentDao.getCountOperationsByCashPoint(cashPoint);
    }
}
