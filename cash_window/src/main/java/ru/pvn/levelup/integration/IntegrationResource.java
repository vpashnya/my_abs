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

    public final String FINCORE_PAYDOCUMENT = props.getProperty("FINCORE_PAYDOCUMENT");
    public final String FINCORE_ACCOUNTREST = props.getProperty("FINCORE_ACCOUNTREST");
}
