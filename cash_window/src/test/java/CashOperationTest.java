import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.utils.CashOperationUtils;
import ru.pvn.levelup.utils.CashPointUtils;

import java.math.BigDecimal;

@DisplayName("Операции с кассовыми документами")
public class CashOperationTest {
    @Test
    @DisplayName("Создание и исполнение кассового документа")
    public void createAndExecuteTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashOperation cashOperation = new CashOperation(null
                    , null
                    , "40702810000000000000"
                    , new BigDecimal(10)
                    , CashOperation.Direction.TO_BANK
                    , null
                    , null);
            CashOperationUtils.saveAndExecute(cashOperation);
            System.out.println(cashOperation);
        });
    }
}
