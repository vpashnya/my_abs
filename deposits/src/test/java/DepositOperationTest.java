import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.entities.Deposit;
import ru.pvn.levelup.entities.DepositOperation;
import ru.pvn.levelup.utils.DepositOperationUtils;
import ru.pvn.levelup.utils.DepositUtils;

@DisplayName("Тест по депозитным операциям")
public class DepositOperationTest {
    @Test
    @DisplayName("Причисление процентов")
    @Disabled
    public void addProcentTest() {
        Assertions.assertDoesNotThrow(() -> {
            Deposit deposit = DepositUtils.getDepositById(41);
            System.out.println(deposit);
            System.out.println(DepositUtils.getRest(deposit));
            System.out.println("Причисление процентов");
            DepositOperationUtils.addProcent(deposit, DepositUtils.getOperDay());
            System.out.println(DepositUtils.getRest(deposit));

        });
    }

    @Test
    @DisplayName("Закрытие вкладв")
    @Disabled
    public void closeDepositTest() {
        Assertions.assertDoesNotThrow(() -> {
            Deposit deposit = DepositUtils.getDepositById(43);
            System.out.println(deposit);
            System.out.println(DepositUtils.getRest(deposit));
            System.out.println("Закрытие вклада...");
            DepositOperationUtils.closeDepositOperations(deposit,DepositUtils.getOperDay() );
            System.out.println(deposit);
            System.out.println(DepositUtils.getRest(deposit));
        });
    }
}
