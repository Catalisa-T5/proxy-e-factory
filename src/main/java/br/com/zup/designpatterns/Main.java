package br.com.zup.designpatterns;

import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        OpenMeteoClimaService climaService = new OpenMeteoClimaService(client, -23.61711259032755, -46.687286901374115);

        ClimaAtual clima = climaService.getClima();
        System.out.println(clima);
    }
}