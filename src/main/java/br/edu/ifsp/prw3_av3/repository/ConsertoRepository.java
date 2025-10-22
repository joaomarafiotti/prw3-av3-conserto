package br.edu.ifsp.prw3_av3.repository;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    // GET completo paginado (p2) — versao apenas ativos:
    Page<Conserto> findAllByAtivoTrue(Pageable paginacao);

    // GET parcial sem paginacao (parte 2/3) — somente ativos
    List<Conserto> findByAtivoTrue();
}