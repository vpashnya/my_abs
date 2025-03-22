package ru.pvn.levelup.dbhelpers;

import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

@UtilityClass
public class DBHelperDeposits {
    private static final Configuration CONFIGURATION = new Configuration().configure("hibernate-deposits.cfg.xml");

    public EntityManager getEntityManager() {
        return CONFIGURATION.buildSessionFactory().createEntityManager();

    }
}
