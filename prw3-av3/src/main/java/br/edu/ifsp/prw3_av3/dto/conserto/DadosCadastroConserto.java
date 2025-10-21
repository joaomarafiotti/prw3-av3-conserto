package br.edu.ifsp.prw3_av3.dto.conserto;

import jakarta.validation.constraints.*;

public record DadosCadastroConserto(

    // datas: "dd/MM/yyyy" (apenas formato)
    @NotBlank
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
    String dataEntrada,

    // opcional, mas se vier precisa estar no formato
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
    String dataSaida,

    // obrigatórios
    @NotBlank String mecanicoNome,
    @NotNull  Integer mecanicoAnos,

    // veículo: obrigatórios + ano "xxxx"
    @NotBlank String veiculoMarca,
    @NotBlank String veiculoModelo,
    @NotBlank @Pattern(regexp = "\\d{4}") String veiculoAno,

    // Parte 2: campo opcional (virá da V2)
    String veiculoCor
) {}
