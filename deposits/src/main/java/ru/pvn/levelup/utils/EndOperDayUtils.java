package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.entities.Deposit;

import java.time.LocalDate;

@UtilityClass
public class EndOperDayUtils {
    public void runEndOperDay() {
        final LocalDate curDay = DepositUtils.getOperDay();

        DepositUtils
                .getAllDeposits()
                .stream()
                .filter(deposit -> deposit.getStatus() == Deposit.Status.WORK)
                .forEach(deposit -> {
                    try {
                        DepositOperationUtils.addProcent(deposit, curDay);
                    } catch (Exception e) {
                        System.out.println("Ошибка начисления процентов по депозиту " + deposit);
                    }

                    if (deposit.getDateOpen().plusDays(deposit.getDuration()).compareTo(curDay) <= 0) {
                        System.out.println("to close " + deposit);
                        try {
                            DepositUtils.closeDeposit(deposit, curDay);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
    }
}
