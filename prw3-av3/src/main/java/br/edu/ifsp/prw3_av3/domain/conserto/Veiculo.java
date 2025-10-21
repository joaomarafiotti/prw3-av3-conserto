package br.edu.ifsp.prw3_av3.domain.conserto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class Veiculo {
    private String marca;
    private String modelo;
    private String ano; // "xxxx"
}