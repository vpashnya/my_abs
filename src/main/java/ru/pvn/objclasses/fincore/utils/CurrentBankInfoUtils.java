package ru.pvn.objclasses.fincore.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.fincore.CurrentBankInfoDaoImpl;
import ru.pvn.objclasses.fincore.CurrentBankInfo;

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
