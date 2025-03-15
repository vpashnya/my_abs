package cashwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@DisplayName("Создание объектов кассового модуля")
public class CreateObjectsTest {
    @Test
    @DisplayName("Создание нового внутреннего кассового документа")
    public void createCashDoc() {
        Random random = new Random();
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; i++) {

            }
        });
    }
}
