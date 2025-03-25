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

    private PayDocumentDaoImpl payDocumentDao = PayDocumentDaoImpl.getCurrentDao();

    public void savePayDocument(PayDocument payDocument) {
        if (payDocument.getDebet().getId() != null) {
            payDocument.setDebet(AccountUtils.getAccoutById(payDocument.getDebet().getId()));
        } else if (payDocument.getDebet().getAccNum() != null) {
            payDocument.setDebet(AccountUtils.getAccoutByNum(payDocument.getDebet().getAccNum()));
        } else {
            throw new RuntimeException("Не возможно определить счет по дебету");
        }

        if (payDocument.getCredit().getId() != null) {
            payDocument.setCredit(AccountUtils.getAccoutById(payDocument.getCredit().getId()));
        } else if (payDocument.getCredit().getAccNum() != null) {
            payDocument.setCredit(AccountUtils.getAccoutByNum(payDocument.getCredit().getAccNum()));
        } else {
            throw new RuntimeException("Не возможно определить счет по кредиту");
        }

        payDocument.setState(PayDocument.State.NEW);
        payDocument.setDocDate(CurrentBankInfoUtils.getBankInfo().getOperDay());
        payDocumentDao.save(payDocument);
    }

    public PayDocument createPayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date, String purpose) {
        PayDocument payDocument = new PayDocument(null, debet, credit, docSum, date, PayDocument.State.NEW, null, purpose);
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

    public PayDocument createAndExecutePayDocument(Account debet, Account credit, BigDecimal docSum, LocalDate date, String purpose) {
        if (docSum.compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Сумма документа не может быть 0");
        }
        return executePayDocument(createPayDocument(debet, credit, docSum, date, purpose));

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
