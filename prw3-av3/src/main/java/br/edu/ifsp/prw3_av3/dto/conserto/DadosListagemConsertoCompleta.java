package br.edu.ifsp.prw3_av3.dto.conserto;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;

public record DadosListagemConsertoCompleta(
        Long id,
        String dataEntrada,
        String dataSaida,
        String mecanicoNome,
        Integer mecanicoAnos,
        String veiculoMarca,
        String veiculoModelo,
        String veiculoAno,
        String veiculoCor
) {
    public DadosListagemConsertoCompleta(Conserto c) {
        this(
            c.getId(),
            c.getDataEntrada(),
            c.getDataSaida(),
            c.getMecanico().getNome(),
            c.getMecanico().getAnosExperiencia(),
            c.getVeiculo().getMarca(),
            c.getVeiculo().getModelo(),
            c.getVeiculo().getAno(),
            c.getVeiculo().getCor()
        );
    }
}