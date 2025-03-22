package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.PayDocument;

import java.math.BigDecimal;

@UtilityClass
public class EndDayUtils {
    public void SendAllCash2CorAccount() {
        CashPointUtils.getAllCashPoints().stream().forEach(cashPoint -> {
            Account accCashPoint = CashOperationUtils.getAccountByNum(cashPoint.getPointAccountNum());
            if (BigDecimal.ZERO.compareTo(accCashPoint.getRest()) != 0) {
                PayDocument payDocument = new PayDocument(
                        null
                        , new Account(1, null, null, null, null)
                        , accCashPoint
                        , accCashPoint.getRest().abs()
                        , null
                        , null
                        , null
                        , "Инкассация "
                );

                CashOperationUtils.sendPayDocument2FinCore(payDocument);

            }

        });

    }
}
