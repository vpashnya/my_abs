import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.utils.EndOperDayUtils;

@DisplayName("Проверка завершения дня ")
public class EndDayTest {
    @Test
    @DisplayName("Завершения дня")
    @Disabled
    public void endDatTest() {
        Assertions.assertDoesNotThrow(()->{
            EndOperDayUtils.runEndOperDay();
        });
    }
}
