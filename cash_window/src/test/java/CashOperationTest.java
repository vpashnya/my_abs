import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import ru.pvn.levelup.entities.CashOperation;
import ru.pvn.levelup.utils.CashOperationUtils;
import ru.pvn.levelup.utils.CashPointUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@DisplayName("Операции с кассовыми документами")
public class CashOperationTest {
    private static ObjectMapper jsonMapper;

    @BeforeAll
    public static void beforeAll() {
        jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

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
                    , null
                    , null);
            CashOperationUtils.saveAndExecute(cashOperation);
            System.out.println(cashOperation);
        });
    }

    @Test
    @DisplayName("Json представления кассовой операции")
    public void operation2JsonTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashOperation cashOperation = new CashOperation(null
                    , null
                    , "40702810000000000000"
                    , new BigDecimal(10)
                    , CashOperation.Direction.TO_BANK
                    , null
                    , null
                    , "XXXXXX");

            System.out.println(jsonMapper.writeValueAsString(cashOperation));
        });
    }

    @Test
    @DisplayName("Операция по кассам")
    public void operationByCashPointTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashOperationUtils.getAllCashOperations()
                    .stream()
                    .collect(Collectors.groupingBy(it -> {
                        return ((CashOperation) it).getCashPoint();
                    }, Collectors.counting()))
                    .entrySet()
                    .stream().forEach(System.out::println);


        });
    }

    @Test
    @DisplayName("Конвертация кассовой операции в платежный документ")
    public void cashOperation2payDocumentTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashOperation cashOperation1 = new CashOperation(null
                    , CashPointUtils.getAllCashPoints().getFirst()
                    , "40702810000000000000"
                    , new BigDecimal(10)
                    , CashOperation.Direction.TO_BANK
                    , null
                    , null
                    , null
            );
            System.out.println(CashOperationUtils.convertToPayDocument(cashOperation1));

            CashOperation cashOperation2 = new CashOperation(null
                    , CashPointUtils.getAllCashPoints().getLast()
                    , "40702810000000000000"
                    , new BigDecimal(10)
                    , CashOperation.Direction.FROM_BANK
                    , null
                    , null
                    , null
            );
            System.out.println(CashOperationUtils.convertToPayDocument(cashOperation2));

        });
    }


    @Test
    @DisplayName("Нормализация кассового счета")
    @Disabled
    public void normCashPointAccTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashPointUtils.getAllCashPoints().stream().forEach(
                    (it) -> {
                        CashOperationUtils.normalizeCashAccountRest(it, new BigDecimal(10));

                    });
        });
    }

}
