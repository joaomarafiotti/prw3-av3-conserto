package br.edu.ifsp.prw3_av3.dto.conserto;

import jakarta.validation.constraints.*;

public record DadosCadastroConserto(

        // datas: dd/MM/yyyy (apenas formato)
        @NotBlank(message = "dataEntrada é obrigatória")
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "dataEntrada deve estar no formato dd/MM/yyyy")
        String dataEntrada,

        // se vier precisa estar no formato
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "dataSaida deve estar no formato dd/MM/yyyy")
        String dataSaida,

        // obrigatorios
        @NotBlank(message = "mecanicoNome é obrigatório")
        String mecanicoNome,

        @NotNull(message = "mecanicoAnos é obrigatório")
        @Min(value = 0, message = "mecanicoAnos não pode ser negativo")
        Integer mecanicoAnos,

        // veiculo: obrigatorios + ano xxxx
        @NotBlank(message = "veiculoMarca é obrigatória")
        String veiculoMarca,

        @NotBlank(message = "veiculoModelo é obrigatório")
        String veiculoModelo,

        @NotBlank(message = "veiculoAno é obrigatório")
        @Pattern(regexp = "\\d{4}", message = "veiculoAno deve estar no formato yyyy (4 dígitos)")
        String veiculoAno,

        // Parte 2: campo opcional (vai vim da V2)
        String veiculoCor
) {}