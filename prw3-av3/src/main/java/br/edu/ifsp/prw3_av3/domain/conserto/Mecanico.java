package br.edu.ifsp.prw3_av3.domain.conserto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @NoArgsConstructor @AllArgsConstructor
public class Mecanico {
    private String nome;
    private Integer anosExperiencia;

    public void atualizar(String novoNome, Integer novosAnos) {
        if (novoNome != null) this.nome = novoNome;
        if (novosAnos != null) this.anosExperiencia = novosAnos;
    }
}