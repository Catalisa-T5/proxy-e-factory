package br.com.zup.designpatterns;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

public class OpenMeteoClimaService implements ClimaService {
    private String url;
    private HttpClient client;

    OpenMeteoClimaService(HttpClient client, Double lat, Double lon) {
        url = String.format(Locale.ENGLISH, "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&current_weather=true",lat,lon);
        this.client = client;
    }

    public ClimaAtual getClima() throws Exception {
        HttpRequest request = HttpRequest.newBuilder(new URI(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        OpenMeteoResponse responseData = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response.body(), OpenMeteoResponse.class);

        OpenMeteoCurrentWeatherResponse d = responseData.current_weather;

        return new ClimaAtual(d.temperature, d.winddirection, d.windspeed);
    }
}

@ToString
class OpenMeteoResponse {
    public OpenMeteoCurrentWeatherResponse current_weather;
}

@ToString
class OpenMeteoCurrentWeatherResponse {
    public Double temperature;
    public Double windspeed;
    public Double winddirection;
}