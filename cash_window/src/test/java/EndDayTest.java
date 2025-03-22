import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.utils.EndDayUtils;

@DisplayName("Получение информации о кассовых окнах")
public class EndDayTest {
    @Test
    @DisplayName("Инкассация")
    @Disabled
    public void cash2CorAccTest() {
        Assertions.assertDoesNotThrow(() -> {
            EndDayUtils.SendAllCash2CorAccount();
        });
    }
}
