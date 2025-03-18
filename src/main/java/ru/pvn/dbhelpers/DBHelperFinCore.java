package ru.pvn.dbhelpers;

import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;

@UtilityClass
public class DBHelperFinCore {
    private static final Configuration CONFIGURATION = new Configuration().configure("hibernate-fincore.cfg.xml");

    public EntityManager getEntityManager() {
        return CONFIGURATION.buildSessionFactory().createEntityManager();

    }
}
