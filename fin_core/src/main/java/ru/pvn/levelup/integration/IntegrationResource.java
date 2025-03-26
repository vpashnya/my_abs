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

    public static final String CASHWINDOW_ENDDAY = props.getProperty("CASHWINDOW_ENDDAY");
    public static final String DEPOSITS_ENDDAY = props.getProperty("DEPOSITS_ENDDAY");
}
