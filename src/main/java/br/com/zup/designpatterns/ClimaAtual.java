package br.com.zup.designpatterns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ClimaAtual {
    private Double temperatura;
    private Double direcaoDoVento;
    private Double velocidadeDoVento;
}
