package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.AccountDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;

import java.util.List;

@UtilityClass
public class AccountUtils {
    public static final List<String> BALANCE_POSITIONS_ACTIVE = List.of(
            "47423", "47425"
            , "45801", "42802", "45803", "45804", "45805"
            , "20202"
    );

    public static final List<String> BALANCE_POSITIONS_PASIVE = List.of(
            "40701", "40702", "40703"
            , "42301", "42302", "42303", "42304", "42305"
            , "40802", "40817", "40821"
    );

    private static AccountDaoImpl accountDao = AccountDaoImpl.getDao();


    public Account openInBalancePosition(String balancePosition, Client client) {
        Client clientChecked = ClientUtils.getClientById(client.getId());

        if (clientChecked == null) {
            throw new RuntimeException("Передан id несуществующего клиента");
        }

        Account.AccType accType = null;
        if (BALANCE_POSITIONS_ACTIVE.contains(balancePosition)) {
            accType = Account.AccType.ACTIVE;
        }

        if (BALANCE_POSITIONS_PASIVE.contains(balancePosition)) {
            accType = Account.AccType.PASSIVE;
        }

        if (accType == null) {
            throw new RuntimeException("Нельзя открывать счет в балансовой позиции " + balancePosition);
        }

        String accNum = accountDao.getNextAccountNum(balancePosition);

        return accountDao.findOrCreate(null, accType, accNum, client);

    }

    public Account getAccoutById(Integer id) {
        return accountDao.get(id);
    }

    public Account getAccoutByNum(String accNum) {
        return accountDao.findByNum(accNum);
    }

    public Account getAccount(Account.AccType accType, String accNum, Client client) {
        Account account = accountDao.findOrCreate(null, accType, accNum, client);
        return account;
    }

    public Integer getAccountCount() {
        return accountDao.count();
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }


}
