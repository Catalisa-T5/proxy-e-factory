package br.com.zup.designpatterns;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        ClimaServiceFactory factory = new CachedClimaServiceFactory(client, false);

        ClimaService climaServiceZup = factory.makeClimaService(-23.61711259032755, -46.687286901374115);
        ClimaService climaServiceCuritiba = factory.makeClimaService(-25.44149356186056, -49.26782554583463);

        for (int i = 0; i < 200; i++) {
            ClimaAtual climaZup = climaServiceZup.getClima();
            System.out.println(climaZup);

            ClimaAtual climaCuritiba = climaServiceCuritiba.getClima();
            System.out.println(climaCuritiba);

            Thread.sleep(1000);
        }
    }
}