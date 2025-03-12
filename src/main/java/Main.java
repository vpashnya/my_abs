import ru.pvn.dao.fincore.AccountDaoImpl;
import ru.pvn.objclasses.fincore.Account;
import ru.pvn.objclasses.fincore.Client;
import ru.pvn.objclasses.fincore.utils.*;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.stream.Stream;

//Класс не относится к функциональности используется для отладки
public class Main {
    public static void main(String[] args) {

        AccountDaoImpl.getDao().getAll().stream().forEach(System.out::println);
        //System.out.println(CurrentBankInfoUtils.getBankInfo());
        /*Client bank = ClientUtils.getClientByParams("Колхоз Банк", "1234567890");
        Client moskowAdm = ClientUtils.getClientByParams("Администрация Москвы", "1234567891");
        Client rusGov = ClientUtils.getClientByParams("Правительство РФ", "1234567892");

        Stream.of(bank, moskowAdm, rusGov).forEach(System.out::println);

        AccountUtils.openInBalancePosition("40701", moskowAdm);

        AccountUtils.BALANCE_POSITIONS_ACTIVE.stream().forEach(it->{
            AccountUtils.openInBalancePosition(it, moskowAdm);
        });

       Account corAcc = AccountUtils.getAccount(Account.AccType.MIXED, "30102810000000000001", bank);
        Account mskAcc = AccountUtils.getAccount(Account.AccType.PASSIVE, "40701810000000000001", moskowAdm);
        Account govRFAcc = AccountUtils.getAccount(Account.AccType.PASSIVE, "40701810000000000002", rusGov);
        Account comisAcc = AccountUtils.getAccount(Account.AccType.ACTIVE, "47423810000000000002", bank);

        Stream.of(corAcc, mskAcc, govRFAcc).forEach(System.out::println);
*/
        //PayDocumentUtils.createAndExecutePayDocument(mskAcc, corAcc , new BigDecimal("1200.01"), LocalDate.of(2025, 02, 06));
      /*  PayDocumentUtils.createAndExecutePayDocument(corAcc, mskAcc, new BigDecimal("200.02"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(corAcc, mskAcc, new BigDecimal("300.03"), LocalDate.of(2025, 02, 02));

        PayDocumentUtils.createAndExecutePayDocument(corAcc, govRFAcc, new BigDecimal("400.04"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(corAcc, govRFAcc, new BigDecimal("500.05"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(corAcc, govRFAcc, new BigDecimal("100.01"), LocalDate.of(2025, 02, 02));

        PayDocumentUtils.createAndExecutePayDocument(mskAcc, govRFAcc, new BigDecimal("320.77"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(govRFAcc, corAcc, new BigDecimal("125.99"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(govRFAcc, mskAcc, new BigDecimal("200.01"), LocalDate.of(2025, 02, 02));
        PayDocumentUtils.createAndExecutePayDocument(corAcc, mskAcc, new BigDecimal("100.04"), LocalDate.of(2025, 02, 03));


        PayDocumentUtils.createAndExecutePayDocument(corAcc, comisAcc, new BigDecimal("1500.0"), LocalDate.of(2025, 02, 06));
*/

        /*PayDocumentUtils.createAndExecutePayDocument(govRFAcc, comisAcc, new BigDecimal("500.0"), LocalDate.of(2025, 02, 05));


        System.out.println(corAcc.getAccNum() + " : " + FinRecordUtils.getRest(corAcc, LocalDate.of(2025, 02, 03)));
        System.out.println(govRFAcc.getAccNum() + " : " + FinRecordUtils.getRest(govRFAcc, LocalDate.of(2025, 02, 02)));
        System.out.println(govRFAcc.getAccNum() + " : " + FinRecordUtils.getRest(govRFAcc, LocalDate.of(2025, 02, 05)));
        System.out.println(mskAcc.getAccNum() + " : " + FinRecordUtils.getRest(mskAcc, LocalDate.of(2025, 02, 02)));
        System.out.println(mskAcc.getAccNum() + " : " + FinRecordUtils.getRest(mskAcc, LocalDate.of(2025, 02, 05)));
        System.out.println(comisAcc.getAccNum() + " : " + FinRecordUtils.getRest(comisAcc, LocalDate.of(2025, 02, 02)));
        System.out.println(comisAcc.getAccNum() + " : " + FinRecordUtils.getRest(comisAcc, LocalDate.of(2025, 02, 05)));
*/

        //System.out.println(mskAcc.getAccNum() + " : " + FinRecordUtils.getRest(mskAcc, LocalDate.of(2025, 02, 06)));
        //System.out.println(comisAcc.getAccNum() + " : " + FinRecordUtils.getRest(comisAcc, LocalDate.of(2025, 02, 06)));
        // FinRecordUtils.viewAllRecords();


    }
}