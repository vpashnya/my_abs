package fincore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.objclasses.fincore.utils.ClientUtils;

@DisplayName("Создание объектов фин ядра")
public class CreateObjectsTest {
    @Test
    @DisplayName("Создание/получения клиентов")
    public void createClients() {
        Assertions.assertDoesNotThrow(() -> {
            ClientUtils.getClientByParams("Колхоз Банк", "1234567890");
            ClientUtils.getClientByParams("Администрация Москвы", "1234567891");
            ClientUtils.getClientByParams("Правительство РФ", "1234567892");
        });

    }
}
