package br.edu.ifsp.prw3_av3.domain.conserto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class Mecanico {
    private String nome;
    private Integer anosExperiencia;
}