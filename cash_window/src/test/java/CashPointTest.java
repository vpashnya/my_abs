import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.dao.CashPointDaoImpl;
import ru.pvn.levelup.entities.CashPoint;
import ru.pvn.levelup.utils.CashOperationUtils;
import ru.pvn.levelup.utils.CashPointUtils;

import java.util.List;
import java.util.Random;

@DisplayName("Получение информации о кассовых окнах")
public class CashPointTest {
    @Test
    @DisplayName("Получение информации о кассовых окнах")
    public void getCashPointsTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashPointDaoImpl.getDao().getAll().stream().forEach(System.out::println);

        });
    }

    @Test
    @DisplayName("Рандомные кассы")
    public void randomCashPointTest() {
        Assertions.assertDoesNotThrow(() -> {
            Random random = new Random();
            List<CashPoint> cashPointList = CashPointUtils.getAllCashPoints();
            System.out.println("cashPointList.size() : " + cashPointList.size());
            for (int i = 0; i < 10; i++) {
                System.out.println(cashPointList.get(random.nextInt(cashPointList.size())));
            }
        });
    }

    @Test
    @DisplayName("Количество документов в кассах")
    public void operationInCashPointTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashPointUtils.getAllCashPoints().stream().forEach(it->{
                System.out.println(it + " : " + CashOperationUtils.getCountOperationsByCashPoint(it));
            });
        });
    }
}
