package ru.pvn.dao.fincore;

import ru.pvn.dao.AbstractMyAbsDao;
import ru.pvn.dbhelpers.DBHelperFinCore;
import ru.pvn.objclasses.fincore.PayDocument;

public class PayDocumentDaoImpl extends AbstractMyAbsDao<PayDocument> {
    private static PayDocumentDaoImpl currentDao = new PayDocumentDaoImpl();

    private PayDocumentDaoImpl() {
        super(PayDocument.class, DBHelperFinCore.getEntityManager());
    }

    public static PayDocumentDaoImpl getDao() {
        return currentDao;
    }
}
