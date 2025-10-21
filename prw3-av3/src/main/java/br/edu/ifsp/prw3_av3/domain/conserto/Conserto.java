package br.edu.ifsp.prw3_av3.domain.conserto;

import br.edu.ifsp.prw3_av3.dto.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosCadastroConserto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "consertos")
@Entity(name = "Conserto")
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Conserto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataEntrada; // "dd/MM/yyyy"
    private String dataSaida;   // "dd/MM/yyyy" (pode ser nulo)

    @Embedded
    private Mecanico mecanico;

    @Embedded
    private Veiculo veiculo;

    private Boolean ativo; // V3

    // Construtor que você já deve ter usado a partir do DTO de cadastro:
    public Conserto(DadosCadastroConserto dto) {
        this.dataEntrada = dto.dataEntrada();
        this.dataSaida   = dto.dataSaida();
        this.mecanico    = new Mecanico(dto.mecanicoNome(), dto.mecanicoAnos());
        this.veiculo     = new Veiculo(dto.veiculoMarca(), dto.veiculoModelo(), dto.veiculoAno(), dto.veiculoCor());
        this.ativo       = true; // novo registro começa ativo
    }

    // Atualiza somente os campos permitidos na parte 3
    public void atualizar(DadosAtualizacaoConserto dto) {
        if (dto.dataSaida() != null) this.dataSaida = dto.dataSaida();
        if (dto.mecanicoNome() != null || dto.mecanicoAnos() != null) {
            this.mecanico.atualizar(dto.mecanicoNome(), dto.mecanicoAnos());
        }
    }

    // Exclusão lógica
    public void excluir() {
        this.ativo = false;
    }
}