import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.dao.DepositDaoImpl;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;
import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.utils.DepositOperationUtils;
import ru.pvn.levelup.utils.DepositUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@DisplayName("Тесты с депозитами")
public class DepositTest {

    @Test
    @DisplayName("Сохранение депозита в БД через DAO")
    @Disabled
    public void saveDepositTest() {
        Assertions.assertDoesNotThrow(() -> {
            Deposit deposit = new Deposit(
                    null
                    , 3
                    , "Клиент 1"
                    , 3
                    , "42301"
                    , BigDecimal.ONE
                    , new BigDecimal("0.01")
                    , 50
                    , LocalDate.of(2025, 1, 1)
                    , Deposit.Status.NEW
                    , Deposit.PayTo.TO_CASH
                    , null
            );
            DepositDaoImpl.getCurrentDao().save(deposit);
            System.out.println(deposit);
        });
    }

    @Test
    @DisplayName("Получение остатков по счетам депозитов")
    @Disabled
    public void getDepositRestTest() {
        Assertions.assertDoesNotThrow(() -> {
            DepositUtils.getAllDeposits().forEach(deposit -> {
                System.out.println(deposit.getAccountNum() + " : " + DepositUtils.getRest(deposit));
            });
        });
    }

    @Test
    @DisplayName("Получение клиента по ИНН")
    @Disabled
    public void getClientByINNTest() {
        Assertions.assertDoesNotThrow(() -> {
            Client client = DepositUtils.getClient("8605000012", "Бармалей Фарисович");
            System.out.println(client);
        });
    }

    @Test
    @DisplayName("Открытие счета по сроку")
    @Disabled
    public void openAccountTest() {
        Assertions.assertDoesNotThrow(() -> {
            Account account = DepositUtils.getAccount(1, new Client(4, null, null));
            System.out.println(account);
        });
    }

    @Test
    @DisplayName("Текущий опердень")
    @Disabled
    public void getOperDayTest() {
        Assertions.assertDoesNotThrow(() -> {
           LocalDate operDay = DepositUtils.getOperDay();
           System.out.println(operDay);
        });
    }



}
