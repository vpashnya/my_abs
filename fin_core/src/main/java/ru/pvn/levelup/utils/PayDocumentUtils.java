package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.PayDocumentDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.PayDocument;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class PayDocumentUtils {

    private PayDocumentDaoImpl payDocumentDao = PayDocumentDaoImpl.getDao();

    public void savePayDocument(PayDocument payDocument) {
        payDocument.setDebet(AccountUtils.getAccoutById(payDocument.getDebet().getId()));
        payDocument.setCredit(AccountUtils.getAccoutById(payDocument.getCredit().getId()));
        payDocument.setState(PayDocument.State.NEW);
        payDocument.setDocDate(CurrentBankInfoUtils.getBankInfo().getOperDay());
        payDocumentDao.save(payDocument);
    }

    public PayDocument createPayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date) {
        PayDocument payDocument = new PayDocument(null, debet, credit, docSum, date, PayDocument.State.NEW, null);
        payDocumentDao.save(payDocument);
        return payDocument;
    }

    public PayDocument executePayDocument(PayDocument document) {
        try {
            FinRecordUtils.createFinRecords(document);
            document.setState(PayDocument.State.EXECUTED);
        } catch (RuntimeException e) {
            document.setState(PayDocument.State.REFUSED);
            document.setRefuseReason(e.getMessage());
        }

        payDocumentDao.save(document);
        return document;
    }

    public PayDocument createAndExecutePayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date) {
        if (docSum.compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Сумма документа не может быть 0");
        }
        return executePayDocument(createPayDocument(debet, credit, docSum, date));

    }

    public Integer getPayDocsCount() {
        return payDocumentDao.count();
    }

    public List<PayDocument> getAllPayDocuments() {
        return payDocumentDao.getAll();
    }

    public PayDocument getPayDocumentById(int id) {
        return payDocumentDao.get(id);
    }

}
