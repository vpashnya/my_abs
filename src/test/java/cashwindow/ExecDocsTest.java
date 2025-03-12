package cashwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pvn.objclasses.cashwindow.CashDocumentInternal;
import ru.pvn.objclasses.cashwindow.utils.CashDocumentInternalUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@DisplayName("Создание объектов кассового модуля")
public class ExecDocsTest {
    @Test
    @DisplayName("Отказ документа")
    public void refuseDoc() {
        Random random = new Random();
        CashDocumentInternal cashDoc = CashDocumentInternalUtils.createCashDocumentIn(random.nextInt(1000), random.nextInt(1000), new BigDecimal(random.nextInt(1000)), LocalDate.now());
        Assertions.assertDoesNotThrow(() -> {
            CashDocumentInternalUtils.refuceDoc(cashDoc.getId(), "Ошибка проведения документа!!!");
        });
    }

    @Test
    @DisplayName("Проведение документа")
    public void execDoc() {
        Random random = new Random();
        CashDocumentInternal cashDoc = CashDocumentInternalUtils.createCashDocumentIn(random.nextInt(1000), random.nextInt(1000), new BigDecimal(random.nextInt(1000)), LocalDate.now());
        Assertions.assertDoesNotThrow(() -> {
            CashDocumentInternalUtils.execDoc(cashDoc.getId());
        });
    }
}
