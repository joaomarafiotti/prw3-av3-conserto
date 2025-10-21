package br.edu.ifsp.prw3_av3.controller;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import br.edu.ifsp.prw3_av3.domain.conserto.Mecanico;
import br.edu.ifsp.prw3_av3.domain.conserto.Veiculo;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConsertoCompleta;
import br.edu.ifsp.prw3_av3.repository.ConsertoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(
            @RequestBody @Valid DadosCadastroConserto dto,
            UriComponentsBuilder uriBuilder) {

        var mecanico = new Mecanico(dto.mecanicoNome(), dto.mecanicoAnos());
        var veiculo = new Veiculo(
            dto.veiculoMarca(),
            dto.veiculoModelo(),
            dto.veiculoAno(),
            dto.veiculoCor()     // agora incluímos a cor
        );


        var conserto = new Conserto(dto.dataEntrada(), dto.dataSaida(), mecanico, veiculo);
        repository.save(conserto);

        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).build(); // Parte 1: 201 com Location já atende
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<DadosListagemConsertoCompleta>> listarCompleto(
            org.springframework.data.domain.Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemConsertoCompleta::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/dados")
    public ResponseEntity<java.util.List<DadosListagemConserto>> listarParcial() {
        var lista = repository.findAll().stream()
                .map(DadosListagemConserto::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}
