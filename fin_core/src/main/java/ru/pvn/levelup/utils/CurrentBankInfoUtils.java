package ru.pvn.levelup.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.pvn.levelup.dao.CurrentBankInfoDaoImpl;
import ru.pvn.levelup.entities.CurrentBankInfo;
import ru.pvn.levelup.integration.IntegrationResource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UtilityClass
public class CurrentBankInfoUtils {
    private CurrentBankInfoDaoImpl bankInfoDaoImpl = CurrentBankInfoDaoImpl.getDao();

    private CurrentBankInfo bankInfo = bankInfoDaoImpl.get(0);

    public CurrentBankInfo getBankInfo() {
        return bankInfo;
    }

    public void switch2NextDay() {
        endDayCashWindow();
        bankInfoDaoImpl.switch2NextDay(bankInfo);
    }

    @SneakyThrows
    public void endDayCashWindow() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(URI.create(IntegrationResource.CASHWINDOW_ENDDAY))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        httpClient.send(req, HttpResponse.BodyHandlers.ofByteArray());
    }

}
