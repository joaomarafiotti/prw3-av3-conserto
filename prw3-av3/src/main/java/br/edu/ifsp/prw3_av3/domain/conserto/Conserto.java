package br.edu.ifsp.prw3_av3.domain.conserto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "consertos")
@Entity(name = "Conserto")
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Conserto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataEntrada; // "dd/MM/yyyy" (strings na parte 1)
    private String dataSaida;   // opcional

    @Embedded
    private Mecanico mecanico;

    @Embedded
    private Veiculo veiculo;

    public Conserto(String dataEntrada, String dataSaida, Mecanico mecanico, Veiculo veiculo) {
        this.dataEntrada = dataEntrada;
        this.dataSaida   = dataSaida;
        this.mecanico    = mecanico;
        this.veiculo     = veiculo;
    }
}