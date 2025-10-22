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
public class Veiculo {

    // mapeadas via @AttributeOverrides no Conserto
    private String marca;   // NOT NULL (V1)
    private String modelo;  // NOT NULL (V1)
    private String ano;     // yyyy (V1)
    private String cor;     // opcional (V2)
}