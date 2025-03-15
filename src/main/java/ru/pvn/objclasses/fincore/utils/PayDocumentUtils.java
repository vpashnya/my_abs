package ru.pvn.objclasses.fincore.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.fincore.PayDocumentDaoImpl;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.PayDocument;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class PayDocumentUtils {

    private PayDocumentDaoImpl payDocumentDao = PayDocumentDaoImpl.getDao();

    public PayDocument createPayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date) {
        PayDocument payDocument = new PayDocument(null, debet, credit, docSum, date, PayDocument.State.NEW);
        payDocumentDao.save(payDocument);
        return payDocument;
    }

    public PayDocument executePayDocument(PayDocument document) {
        FinRecordUtils.createFinRecords(document);
        document.setState(PayDocument.State.EXECUTED);
        payDocumentDao.save(document);
        return document;
    }

    public PayDocument createAndExecutePayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date) {
        return executePayDocument(createPayDocument(debet, credit, docSum, date));
    }


}
