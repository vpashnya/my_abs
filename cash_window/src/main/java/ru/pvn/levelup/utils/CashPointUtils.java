package ru.pvn.levelup.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CashPointDaoImpl;
import ru.pvn.levelup.entities.CashPoint;

import java.util.List;

@UtilityClass
public class CashPointUtils {
    private static CashPointDaoImpl currentDao = new CashPointDaoImpl();

    public List<CashPoint> getAllCashPoints() {
        return  currentDao.getDao().getAll();
    }

    public CashPoint getCashPointById(Integer id) {
        return currentDao.get(id);
    }
}
