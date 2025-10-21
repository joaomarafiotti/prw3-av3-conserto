package br.edu.ifsp.prw3_av3.domain.conserto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Mecanico {

    // mapeadas via @AttributeOverrides no Conserto
    private String nome;
    private Integer anosExperiencia;

    public void atualizar(String novoNome, Integer novosAnos) {
        if (novoNome != null && !novoNome.isBlank()) {
            this.nome = novoNome;
        }
        if (novosAnos != null && novosAnos >= 0) {
            this.anosExperiencia = novosAnos;
        }
    }
}