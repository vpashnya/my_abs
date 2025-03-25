package ru.pvn.levelup.dao;

import lombok.Getter;
import ru.pvn.levelup.abscore.AbstractAbsDao;
import ru.pvn.levelup.dbhelpers.DBHelperFinCore;
import ru.pvn.levelup.entities.PayDocument;

public class PayDocumentDaoImpl extends AbstractAbsDao<PayDocument> {
    @Getter
    private static PayDocumentDaoImpl currentDao = new PayDocumentDaoImpl();

    private PayDocumentDaoImpl() {
        super(PayDocument.class, DBHelperFinCore.getEntityManager());
    }


}
