package br.com.zup.designpatterns;

public interface ClimaServiceFactory {
    ClimaService makeClimaService(Double lat, Double lon);
}
