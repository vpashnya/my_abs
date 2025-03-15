package ru.pvn.objclasses.fincore.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.fincore.AccountDaoImpl;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.Client;

import java.util.List;

@UtilityClass
public class AccountUtils {
    public static final List<String> BALANCE_POSITIONS_ACTIVE = List.of(
            "47423", "47425"
            , "45801", "42802", "45803", "45804", "45805"
    );

    public static final List<String> BALANCE_POSITIONS_PASIVE = List.of(
            "40701", "40702", "40703"
            , "42301", "42302", "42303", "42304", "42305"
            , "40802", "40817", "40821"
    );

    private static AccountDaoImpl accountDao = (AccountDaoImpl) AccountDaoImpl.getDao();


    public Account openInBalancePosition(String balancePosition, Client client) {
        Account.AccType accType = null;
        if(BALANCE_POSITIONS_ACTIVE.contains(balancePosition)){
            accType = Account.AccType.ACTIVE;
        }

        if(BALANCE_POSITIONS_PASIVE.contains(balancePosition)){
            accType = Account.AccType.PASSIVE;
        }

        if (accType == null){
            throw new RuntimeException("Нельзя открывать счет в балансовой позиции " + balancePosition);
        }

        String accNum = accountDao.getNextAccountNum(balancePosition);

        return accountDao.findOrCreate(null, accType, accNum, client);

    }

    public Account getAccount(Account.AccType accType, String accNum, Client client) {
        Account account = accountDao.findOrCreate(null, accType, accNum, client);
        return account;
    }
}
