import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.dao.AccountDaoImpl;
import ru.pvn.levelup.dao.ClientDaoImpl;
import ru.pvn.levelup.dao.FinRecordDaoImpl;
import ru.pvn.levelup.dao.PayDocumentDaoImpl;

@DisplayName("Получение информации о количестве объектов в модулях")
public class CountTest {
    @Test
    @DisplayName("Количество объектов")
    public void countTest(){
        Assertions.assertDoesNotThrow(()->{
            System.out.println("Клиентов " + ClientDaoImpl.getCurrentDao().count());
            System.out.println("Счетов " + AccountDaoImpl.getCurrentDao().count());
            System.out.println("Платежных документов " + PayDocumentDaoImpl.getCurrentDao().count());
            System.out.println("Проводок " + FinRecordDaoImpl.getCurrentDao().count());
        });
    }
}
