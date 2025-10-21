package br.edu.ifsp.prw3_av3.dto.conserto;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;

public record DadosListagemConserto(
    String dataEntrada, String dataSaida,
    String mecanicoNome,
    String veiculoMarca, String veiculoModelo
) {
    public DadosListagemConserto(Conserto c) {
        this(
            c.getDataEntrada(), c.getDataSaida(),
            c.getMecanico().getNome(),
            c.getVeiculo().getMarca(), c.getVeiculo().getModelo()
        );
    }
}
