package br.com.zup.designpatterns;

public class LoggerClimaService implements ClimaService {

    private final ClimaService climaService;
    private final String message;

    LoggerClimaService(ClimaService climaService, String message) {
        this.climaService = climaService;
        this.message = message;
    }


    @Override
    public ClimaAtual getClima() throws Exception {
        System.out.println(message);
        return climaService.getClima();
    }
}
