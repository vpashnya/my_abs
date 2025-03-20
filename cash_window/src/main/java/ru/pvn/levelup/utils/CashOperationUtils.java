package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CashOperationDaoImpl;
import ru.pvn.levelup.entities.CashOperation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@UtilityClass
public class CashOperationUtils {
    private CashOperationDaoImpl currentDao = CashOperationDaoImpl.getCurrentDao();
    private Random random = new Random();

    public List<CashOperation> getAllCashOperations() {
        return currentDao.getAll();
    }

    public void saveAndExecute(CashOperation cashOperation) {
        cashOperation.setState(CashOperation.State.NEW);

        cashOperation.setCashPoint(CashPointUtils.getAllCashPoints().get(random.nextInt(CashPointUtils.getAllCashPoints().size())));

        currentDao.save(cashOperation);

        if((cashOperation.getSumDoc()==null)||(cashOperation.getSumDoc().equals(BigDecimal.ZERO))){
            cashOperation.setState(CashOperation.State.REFUSED);
            cashOperation.setRefuseReason("Сумма документа 0");
            currentDao.save(cashOperation);
            return;
        }





    }
}
