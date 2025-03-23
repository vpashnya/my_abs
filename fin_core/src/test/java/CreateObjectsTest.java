import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.utils.AccountUtils;
import ru.pvn.levelup.utils.ClientUtils;
import ru.pvn.levelup.utils.CurrentBankInfoUtils;
import ru.pvn.levelup.utils.FinRecordUtils;
import ru.pvn.levelup.utils.PayDocumentUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@DisplayName("Создание объектов фин ядра")
public class CreateObjectsTest {
    final static Account corAcc = CurrentBankInfoUtils.getBankInfo().getCorrAccount();
    final static LocalDate currDate = CurrentBankInfoUtils.getBankInfo().getOperDay();
    final static Random random = new Random();


    @Test
    @DisplayName("Создание кассового счета")
    @Disabled
    public void createCassAccount() {
        Assertions.assertDoesNotThrow(() -> {
            System.out.println("Открыт счет " + AccountUtils.openInBalancePosition("20202", new Client(1, null, null)));
        });
    }

    @Test
    @DisplayName("Создание/получения клиентов")
    @Disabled
    public void createClientsTest() {
        Assertions.assertDoesNotThrow(() -> {
            ClientUtils.getClientByParams(null, "Колхоз Банк", "1234567890");
            ClientUtils.getClientByParams(null, "Администрация Москвы", "1234567891");
            ClientUtils.getClientByParams(null, "Правительство РФ", "1234567892");
        });
    }

    @Test
    @DisplayName("Кредитование пассивных счетов")
    @Disabled
    public void creditPassiveAccountsTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            AccountUtils.getAllAccounts()
                    .stream()
                    .filter((it) -> (it.getAccType() == Account.AccType.PASSIVE))
                    .forEach((it) -> {
                                PayDocumentUtils.createAndExecutePayDocument(corAcc, it, new BigDecimal(random.nextInt(20) + 1), currDate, "Тестовый документ");
                            }
                    );
        });
    }

    @Test
    @DisplayName("Дебетование пассивных счетов")
    @Disabled
    public void debetPassiveAccountsTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            AccountUtils.getAllAccounts()
                    .stream()
                    .filter((it) -> (it.getAccType() == Account.AccType.PASSIVE))
                    .forEach((it) -> {
                                PayDocumentUtils.createAndExecutePayDocument(it, corAcc, new BigDecimal(random.nextInt(10) + 1), currDate, "Тестовый документ");
                            }
                    );
        });
    }

    @Test
    @DisplayName("Кредитование активных счетов")
    @Disabled
    public void creditActiveAccountsTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            final Account corAcc = CurrentBankInfoUtils.getBankInfo().getCorrAccount();
            final LocalDate currDate = CurrentBankInfoUtils.getBankInfo().getOperDay();
            final Random random = new Random();
            AccountUtils.getAllAccounts()
                    .stream()
                    .filter((it) -> (it.getAccType() == Account.AccType.ACTIVE))
                    .forEach((it) -> {
                                if (FinRecordUtils.getRest(it, currDate).compareTo(BigDecimal.ZERO) < 0) {
                                    PayDocumentUtils.createAndExecutePayDocument(corAcc, it, new BigDecimal(random.nextInt(10) + 1), currDate, "Тестовый документ");
                                }

                            }
                    );
        });
    }

    @Test
    @DisplayName("Дебетование активных счетов")
    @Disabled
    public void debetActiveAccountsTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            final Account corAcc = CurrentBankInfoUtils.getBankInfo().getCorrAccount();
            final LocalDate currDate = CurrentBankInfoUtils.getBankInfo().getOperDay();
            final Random random = new Random();
            AccountUtils.getAllAccounts()
                    .stream()
                    .filter((it) -> (it.getAccType() == Account.AccType.ACTIVE))
                    .forEach((it) -> {
                                if (FinRecordUtils.getRest(it, currDate).compareTo(BigDecimal.ZERO) < 0) {
                                    PayDocumentUtils.createAndExecutePayDocument(it, corAcc, new BigDecimal(random.nextInt(5) + 1), currDate, "Тестовый документ");
                                }

                            }
                    );
        });
    }
}
