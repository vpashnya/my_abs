package ru.pvn.levelup.integration;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IntegrationResource {
    public final String FINCORE_ACCOUNTREST = "http://localhost:8080/fin_core_war/accountrest";
    public final String FINCORE_CLIENT = "http://localhost:8080/fin_core_war/client";
    public final String FINCORE_ACCOUNT = "http://localhost:8080/fin_core_war/account";
    public final String FINCORE_OPERDAY = "http://localhost:8080/fin_core_war/operday";
}
