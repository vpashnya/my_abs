package ru.pvn.levelup.integration;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class IntegrationResource {
    final Properties props;

    static {
        try {
            props = new Properties();
            props.load(IntegrationResource.class.getClassLoader().getResourceAsStream("integration.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final String FINCORE_ACCOUNTREST = props.getProperty("FINCORE_ACCOUNTREST");
    public final String FINCORE_CLIENT = props.getProperty("FINCORE_CLIENT");
    public final String FINCORE_ACCOUNT = props.getProperty("FINCORE_ACCOUNT");
    public final String FINCORE_OPERDAY = props.getProperty("FINCORE_OPERDAY");
    public final String FINCORE_PAYDOC = props.getProperty("FINCORE_PAYDOC");
    public final String CASHWINDOW_CASHOPERATION = props.getProperty("CASHWINDOW_CASHOPERATION");
}
