import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.levelup.utils.CurrentBankInfoUtils;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@DisplayName("Получение информации о Банке")
public class BankInfoTest {
    @Test
    @DisplayName("Получение информации о Банке")
    public void checkBankInfo() {
        Assertions.assertDoesNotThrow(() -> {
            System.out.println(CurrentBankInfoUtils.getBankInfo());

        });
    }

    @Test
    @DisplayName("Переключение дня")
    @Disabled
    public void switch2NextDay() {

        LocalDate date1 = CurrentBankInfoUtils.getBankInfo().getOperDay();
        CurrentBankInfoUtils.switch2NextDay();
        LocalDate date2 = CurrentBankInfoUtils.getBankInfo().getOperDay();
        long daysBetween = ChronoUnit.DAYS.between(date2, date1);
        Assertions.assertFalse(daysBetween == 1);

    }
}
