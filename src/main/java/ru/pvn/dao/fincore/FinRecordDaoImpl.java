package ru.pvn.dao.fincore;

import ru.pvn.dao.AbstractMyAbsDao;
import ru.pvn.dbhelpers.DBHelperFinCore;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.FinRecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class FinRecordDaoImpl extends AbstractMyAbsDao<FinRecord> {
    private static FinRecordDaoImpl currentCao = new FinRecordDaoImpl();

    private FinRecordDaoImpl() {
        super(FinRecord.class, DBHelperFinCore.getEntityManager());
    }

    public static FinRecordDaoImpl getDao() {
        return currentCao;
    }

    public BigDecimal getRest(Integer accountId, LocalDate date) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNativeQuery("""
                select coalesce(sum(rec_sum * case rec_type when 0 then -1 else 1 end),0.00)
                from fin_record
                where   account = ?1
                    and rec_date <= ?2
                """);
        query.setParameter(1, accountId);
        query.setParameter(2, date);
        List<BigDecimal> result = query.getResultList();
        return result.get(0);
    }

    private void checkRecordsInFuture(FinRecord record) {
        Query query = getEntityManager().createNativeQuery("""
                select 1
                from fin_record
                where   account = ?1
                    and rec_date > ?2
                fetch first 1 rows only
                """);
        query.setParameter(1, record.getAccount());
        query.setParameter(2, record.getRecDate());
        List<Integer> result = query.getResultList();
        if (!result.isEmpty()) {
            throw new RuntimeException(" По счету %s имеются движения после %s, проведeние документа невозможно".formatted(record.getAccount().getAccNum(), record.getRecDate()));
        }
    }

    private void checkOutSaldo(FinRecord record) {
        BigDecimal rest = getRest(record.getAccount().getId(), record.getRecDate());
        if (record.getRecType() == FinRecord.RecType.DEBET) {
            rest = rest.add(record.getRecSum());
        } else {
            rest = rest.subtract(record.getRecSum());
        }

        int compareVal = rest.compareTo(BigDecimal.ZERO);

        if (compareVal < 0 && record.getAccount().getAccType() == Account.AccType.PASSIVE) {
            throw new RuntimeException("У пассивного счета %s образуется недопустимый остаток %s ".formatted(record.getAccount().getAccNum(), rest));
        } else if (compareVal > 0 && record.getAccount().getAccType() == Account.AccType.ACTIVE) {
            throw new RuntimeException("У активного счета %s образуется недопустимый остаток %s ".formatted(record.getAccount().getAccNum(), rest));
        }

    }

    public void save(FinRecord... records) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            for (FinRecord record : records) {
                checkRecordsInFuture(record);
                checkOutSaldo(record);
                entityManager.persist(record);
            }

            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }

    }

}
