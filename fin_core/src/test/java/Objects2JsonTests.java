import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.entities.Account;
import ru.pvn.levelup.entities.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pvn.levelup.entities.PayDocument;

import java.io.IOException;
import java.math.BigDecimal;

@DisplayName("Сериализация клиентов в Json")
public class Objects2JsonTests {
    private static ObjectMapper jsonMapper;

    @BeforeAll
    public static void beforeAll() {
        jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    @Test
    @DisplayName("Клиент в Json")
    public void client2JsonTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            Client client = new Client(null, "Тестовый клиент", "00000001001");
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(jsonMapper.writeValueAsString(client));
        });

    }

    @Test
    @DisplayName("Счет в Json")
    public void account2JsonTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            Account account = new Account(null, null, "40817", new Client(5, null, null), null);
            System.out.println(jsonMapper.writeValueAsString(account));
        });

    }

    @Test
    @DisplayName("Платежный документ в json")
    public void payDocument2JsonTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> {
            PayDocument payDocument = new PayDocument(null
                    , new Account(1, null, null, null, null)
                    , new Account(10, null, null, null,null)
                    , new BigDecimal(10)
                    , null
                    , null
                    , null
                    , null);
            System.out.println(jsonMapper.writeValueAsString(payDocument));
        });
    }
}
