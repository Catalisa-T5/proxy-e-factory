package br.com.zup.designpatterns;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CachedClimaServiceFactory implements ClimaServiceFactory {

    private final HttpClient httpClient;
    private final boolean mostrarLogs;

    CachedClimaServiceFactory(HttpClient httpClient, boolean mostrarLogs) {
        this.httpClient = httpClient;
        this.mostrarLogs = mostrarLogs;
    }


    @Override
    public ClimaService makeClimaService(Double lat, Double lon) {
        ClimaService climaService = new OpenMeteoClimaService(httpClient, lat, lon);
        if (mostrarLogs) {
            climaService = new LoggerClimaService(climaService, "chamou open-meteo");
        }

        climaService = new CachedClimaService(climaService, Duration.of(10, ChronoUnit.SECONDS));
        if (mostrarLogs) {
            climaService = new LoggerClimaService(climaService, "chamou cache");
        }

        return climaService;
    }
}
