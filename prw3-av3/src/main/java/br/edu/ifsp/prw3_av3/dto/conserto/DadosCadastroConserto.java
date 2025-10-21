package br.edu.ifsp.prw3_av3.dto.conserto;

import jakarta.validation.constraints.*;

public record DadosCadastroConserto(
    @NotBlank String dataEntrada,
    String dataSaida,         // pode ser nulo na parte 1
    @NotBlank String mecanicoNome,
    @NotNull  Integer mecanicoAnos,
    @NotBlank String veiculoMarca,
    @NotBlank String veiculoModelo,
    @NotBlank String veiculoAno // "xxxx"
) {}
