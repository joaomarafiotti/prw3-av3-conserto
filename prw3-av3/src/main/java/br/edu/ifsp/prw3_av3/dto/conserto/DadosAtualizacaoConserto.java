package br.edu.ifsp.prw3_av3.dto.conserto;

import jakarta.validation.constraints.*;

public record DadosAtualizacaoConserto(

        @NotNull(message = "id é obrigatório")
        Long id,

        // opcional, mas se vier precisa estar no formato
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "dataSaida deve estar no formato dd/MM/yyyy")
        String dataSaida,

        String mecanicoNome,

        @Min(value = 0, message = "mecanicoAnos não pode ser negativo")
        Integer mecanicoAnos
) {}