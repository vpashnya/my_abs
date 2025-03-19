package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CurrentBankInfoDaoImpl;
import ru.pvn.levelup.entities.CurrentBankInfo;

@UtilityClass
public class CurrentBankInfoUtils {
    private CurrentBankInfoDaoImpl bankInfoDaoImpl = CurrentBankInfoDaoImpl.getDao();

    private CurrentBankInfo bankInfo = bankInfoDaoImpl.get(0);

    public CurrentBankInfo getBankInfo() {
        return bankInfo;
    }

    public void switch2NextDay() {
        bankInfoDaoImpl.switch2NextDay(bankInfo);
    }

}
