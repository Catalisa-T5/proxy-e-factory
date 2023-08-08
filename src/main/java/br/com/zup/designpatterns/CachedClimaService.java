package br.com.zup.designpatterns;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class CachedClimaService implements ClimaService {

    private final ClimaService climaService;
    private final Duration quantoTempoEntreRequisicoes;

    private Instant ultimaChamada = Instant.MIN;
    private Optional<ClimaAtual> ultimoClima = Optional.empty();

    CachedClimaService(ClimaService climaService, Duration quantoTempoEntreRequisicoes) {
        this.climaService = climaService;
        this.quantoTempoEntreRequisicoes = quantoTempoEntreRequisicoes;
    }

    @Override
    public ClimaAtual getClima() throws Exception {
        Instant now = Instant.now();
        if (ultimoClima.isEmpty() || now.isAfter(ultimaChamada.plus(quantoTempoEntreRequisicoes))) {
            ultimoClima = Optional.of(climaService.getClima());
            ultimaChamada = now;
        }
        return ultimoClima.get();
    }
}
