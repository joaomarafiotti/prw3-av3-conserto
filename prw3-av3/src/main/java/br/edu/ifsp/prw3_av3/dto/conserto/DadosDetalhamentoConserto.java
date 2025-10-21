package br.edu.ifsp.prw3_av3.dto.conserto;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;

public record DadosDetalhamentoConserto(
    Long id,
    String dataEntrada, String dataSaida,
    String mecanicoNome, Integer mecanicoAnos,
    String veiculoMarca, String veiculoModelo, String veiculoAno, String veiculoCor,
    Boolean ativo
) {
    public DadosDetalhamentoConserto(Conserto c) {
        this(
            c.getId(),
            c.getDataEntrada(), c.getDataSaida(),
            c.getMecanico().getNome(), c.getMecanico().getAnosExperiencia(),
            c.getVeiculo().getMarca(), c.getVeiculo().getModelo(), c.getVeiculo().getAno(), c.getVeiculo().getCor(),
            c.getAtivo()
        );
    }
}