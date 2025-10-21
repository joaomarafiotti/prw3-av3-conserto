package br.edu.ifsp.prw3_av3.repository;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {
    // para o GET da listagem completa paginada (se quiser só ativos)
    Page<Conserto> findAllByAtivoTrue(Pageable paginacao);

    // para o GET parcial sem paginação
    List<Conserto> findByAtivoTrue();
}