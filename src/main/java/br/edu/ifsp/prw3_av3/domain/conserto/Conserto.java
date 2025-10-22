package br.edu.ifsp.prw3_av3.domain.conserto;

import br.edu.ifsp.prw3_av3.dto.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosCadastroConserto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "consertos")
@Entity(name = "Conserto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conserto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada", nullable = false, length = 10) // dd/MM/yyyy
    private String dataEntrada;

    @Column(name = "data_saida", length = 10)
    private String dataSaida;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nome",              column = @Column(name = "mecanico_nome", nullable = false, length = 100)),
        @AttributeOverride(name = "anosExperiencia",   column = @Column(name = "mecanico_anos_experiencia", nullable = false))
    })
    private Mecanico mecanico;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "marca",  column = @Column(name = "veiculo_marca",  nullable = false, length = 60)),
        @AttributeOverride(name = "modelo", column = @Column(name = "veiculo_modelo", nullable = false, length = 60)),
        @AttributeOverride(name = "ano",    column = @Column(name = "veiculo_ano",    nullable = false, length = 4)),
        @AttributeOverride(name = "cor",    column = @Column(name = "veiculo_cor",    length = 30)) // (V2)
    })
    private Veiculo veiculo;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo; // V3

    @PrePersist
    private void prePersist() {
        if (ativo == null) ativo = true;
    }

    // Construtor a partir do DTO de cadastro
    public Conserto(DadosCadastroConserto dto) {
        this.dataEntrada = dto.dataEntrada();
        this.dataSaida   = dto.dataSaida();
        this.mecanico    = new Mecanico(dto.mecanicoNome(), dto.mecanicoAnos());
        this.veiculo     = new Veiculo(dto.veiculoMarca(), dto.veiculoModelo(), dto.veiculoAno(), dto.veiculoCor());
        this.ativo       = true; // novo registro começa ativo
    }

    // Atualiza somente os campos permitidos (parte 3)
    public void atualizar(DadosAtualizacaoConserto dto) {
        if (dto.dataSaida() != null) this.dataSaida = dto.dataSaida();
        if (dto.mecanicoNome() != null || dto.mecanicoAnos() != null) {
            if (this.mecanico == null) this.mecanico = new Mecanico(null, null);
            this.mecanico.atualizar(dto.mecanicoNome(), dto.mecanicoAnos());
        }
    }

    // Exclusao logica
    public void excluir() {
        this.ativo = false;
    }
}