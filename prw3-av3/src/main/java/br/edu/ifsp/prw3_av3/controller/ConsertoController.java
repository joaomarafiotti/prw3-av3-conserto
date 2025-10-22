package br.edu.ifsp.prw3_av3.controller;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosAtualizacaoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosCadastroConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosDetalhamentoConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConserto;
import br.edu.ifsp.prw3_av3.dto.conserto.DadosListagemConsertoCompleta;
import br.edu.ifsp.prw3_av3.repository.ConsertoRepository;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    // POST - 201 created + location + body com detalhamento okay
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> cadastrar(
            @RequestBody @Valid DadosCadastroConserto dto,
            UriComponentsBuilder uriBuilder) {

        var conserto = new Conserto(dto);
        repository.save(conserto);

        URI uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConserto(conserto));
    }

    // GET completo paginado — p2 (sem filtro de ativos)
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsertoCompleta>> listarCompleto(Pageable paginacao) {
        Page<DadosListagemConsertoCompleta> page = repository.findAll(paginacao)
                .map(DadosListagemConsertoCompleta::new);
        return ResponseEntity.ok(page);
    }

    // GET parcial (sem paginacao) — parte 2/3 — somente ativos e incluindo id
    @GetMapping("/dados")
    public ResponseEntity<List<DadosListagemConserto>> listarParcial() {
        List<DadosListagemConserto> lista = repository.findByAtivoTrue()
                .stream()
                .map(DadosListagemConserto::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // GET por ID — p3 — detalhamento (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConserto> detalhar(@PathVariable Long id) {
        return repository.findById(id)
                .map(c -> ResponseEntity.ok(new DadosDetalhamentoConserto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT — p3 — altera apenas dataSaida / mecanico.nome / mecanico.anos
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConserto> atualizar(@RequestBody @Valid DadosAtualizacaoConserto dto) {
        var opt = repository.findById(dto.id());
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        var conserto = opt.get();
        conserto.atualizar(dto);

        return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
    }

    // DELETE lpgico — p3 — 204 No Content
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var opt = repository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        var conserto = opt.get();
        conserto.excluir();
        return ResponseEntity.noContent().build();
    }
}