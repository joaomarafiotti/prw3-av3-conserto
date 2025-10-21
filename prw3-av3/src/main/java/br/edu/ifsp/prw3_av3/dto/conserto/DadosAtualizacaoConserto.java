package br.edu.ifsp.prw3_av3.dto.conserto;

import jakarta.validation.constraints.*;

public record DadosAtualizacaoConserto(
    @NotNull Long id,
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}") String dataSaida,
    String mecanicoNome,
    Integer mecanicoAnos
) {}