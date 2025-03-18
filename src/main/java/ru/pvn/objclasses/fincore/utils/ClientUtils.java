package ru.pvn.objclasses.fincore.utils;

import lombok.experimental.UtilityClass;
import ru.pvn.dao.fincore.ClientDaoImpl;
import ru.pvn.objclasses.fincore.Client;

@UtilityClass
public class ClientUtils {
    private static ClientDaoImpl clientDao = ClientDaoImpl.getDao();

    public static Client getClientByParams(String fullName, String inn) {
        return clientDao.findOrCreate(null, fullName, inn);
    }
}
