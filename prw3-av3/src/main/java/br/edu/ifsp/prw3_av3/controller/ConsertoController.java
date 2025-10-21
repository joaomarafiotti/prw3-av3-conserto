package br.edu.ifsp.prw3_av3.controller;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosDetalhamentoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConsertoCompleta;
import br.edu.ifsp.prw3_av3.repository.ConsertoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    // (já tinha) POST com 201 + Location
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> cadastrar(
            @RequestBody @Valid DadosCadastroConserto dto,
            UriComponentsBuilder uriBuilder) {
        var conserto = new Conserto(dto);
        repository.save(conserto);
        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConserto(conserto));
    }

    // (já tinha) GET completo paginado (pode optar por só ativos)
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsertoCompleta>> listarCompleto(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao) // ou findAll(paginacao) se quiser manter todos
                .map(DadosListagemConsertoCompleta::new);
        return ResponseEntity.ok(page);
    }

    // (ALTERADO) GET parcial sem paginação — agora só ATIVOS e com ID
    @GetMapping("/dados")
    public ResponseEntity<List<DadosListagemConserto>> listarParcial() {
        var lista = repository.findByAtivoTrue().stream()
                .map(DadosListagemConserto::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // (NOVO) GET por ID — detalhamento
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConserto> detalhar(@PathVariable Long id) {
        var opt = repository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadosDetalhamentoConserto(opt.get()));
    }

    // (NOVO) PUT — altera apenas dataSaida / mecanico.nome / mecanico.anos
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> atualizar(
            @RequestBody @Valid DadosAtualizacaoConserto dto) {
        var conserto = repository.getReferenceById(dto.id());
        conserto.atualizar(dto);
        return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
    }

    // (NOVO) DELETE lógico — 204 No Content
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var conserto = repository.getReferenceById(id);
        conserto.excluir();
        return ResponseEntity.noContent().build();
    }
}