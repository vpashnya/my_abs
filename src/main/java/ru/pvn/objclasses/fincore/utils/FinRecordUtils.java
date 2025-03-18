package ru.pvn.objclasses.fincore.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.fincore.FinRecordDaoImpl;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.FinRecord;
import ru.pvn.objclasses.fincore.PayDocument;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class FinRecordUtils {

    private FinRecordDaoImpl finRecordDao = FinRecordDaoImpl.getDao();

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

    public BigDecimal getRest(Account account, LocalDate date) {
        return finRecordDao.getRest(account.getId(), date);
    }

    public void viewAllRecords() {
        finRecordDao.getAll().stream().forEach(System.out::println);
    }

}
