package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.entities.PayDocument;

import java.math.BigDecimal;

@UtilityClass
public class EndDayUtils {
    public void sendAllCash2CorAccount() {
        CashPointUtils.getAllCashPoints().stream().forEach(cashPoint -> {
            Account accCashPoint = CashOperationUtils.getAccountByNum(cashPoint.getPointAccountNum());
            if (BigDecimal.ZERO.compareTo(accCashPoint.getRest()) != 0) {
                PayDocument payDocument = new PayDocument(
                        new Account(1)
                        , accCashPoint
                        , accCashPoint.getRest().abs()
                        , "Инкассация "
                );

                CashOperationUtils.sendPayDocument2FinCore(payDocument);

            }

        });

    }
}
