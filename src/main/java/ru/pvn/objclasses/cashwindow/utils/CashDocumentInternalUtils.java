package ru.pvn.objclasses.cashwindow.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.cashwindow.CashDocumentInternalDaoImpl;
import ru.pvn.objclasses.cashwindow.CashDocumentInternal;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class CashDocumentInternalUtils {

    private static CashDocumentInternalDaoImpl cashDocumentDao = CashDocumentInternalDaoImpl.getDao();

    public CashDocumentInternal createCashDocumentIn(Integer debet, Integer credit, BigDecimal docSum, LocalDate date) {
        CashDocumentInternal cashDocumentInternal = new CashDocumentInternal(null, debet, credit, docSum, date, null, CashDocumentInternal.State.NEW);
        cashDocumentDao.save(cashDocumentInternal);
        return cashDocumentInternal;
    }

    public void refuceDoc(Integer cashDocId, String errorInfo) {
        CashDocumentInternal cashDocument = cashDocumentDao.get(cashDocId);
        cashDocument.setState(CashDocumentInternal.State.REFUSED);
        cashDocument.setExecuteInfo(errorInfo);
        cashDocumentDao.save(cashDocument);
    }

    public void execDoc(Integer cashDocId) {
        CashDocumentInternal cashDocument = cashDocumentDao.get(cashDocId);
        cashDocument.setState(CashDocumentInternal.State.EXECUTED);
        cashDocumentDao.save(cashDocument);
    }
}
