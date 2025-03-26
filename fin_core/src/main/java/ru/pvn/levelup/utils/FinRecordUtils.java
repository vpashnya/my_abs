package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.FinRecordDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.FinRecord;
import ru.pvn.levelup.entities.PayDocument;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class FinRecordUtils {

    private FinRecordDaoImpl finRecordDao = FinRecordDaoImpl.getCurrentDao();

    public void createFinRecords(PayDocument document) {
        finRecordDao.save(
                new FinRecord(
                        null
                        , document.getDebet()
                        , document.getDocDate()
                        , document.getDocSum()
                        , FinRecord.RecType.DEBET
                        , document
                )
                , new FinRecord(
                        null
                        , document.getCredit()
                        , document.getDocDate()
                        , document.getDocSum()
                        , FinRecord.RecType.CREDIT
                        , document
                )
        );
    }

    public BigDecimal getRest(Account account) {
        return finRecordDao.getRest(account.getId(), CurrentBankInfoUtils.getBankInfo().getOperDay());
    }

    public BigDecimal getRest(Account account, LocalDate date) {
        return finRecordDao.getRest(account.getId(), date);
    }

    public Integer getFinRecordsCount() {
        return finRecordDao.count();
    }

    public FinRecord getFinRecordById(int id) {
        return finRecordDao.get(id);
    }

    public List<FinRecord> getAllFinRecords() {
        return finRecordDao.getAll();
    }

}
