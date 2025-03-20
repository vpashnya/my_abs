import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.dao.CashPointDaoImpl;

@DisplayName("Получение информации о кассовых окнах")
public class CashPointTest {
    @Test
    @DisplayName("Получение информации о кассовых окнах")
    public void getCashPointsTest() {
        Assertions.assertDoesNotThrow(() -> {
            CashPointDaoImpl.getDao().getAll().stream().forEach(System.out::println);

        });
    }
}
