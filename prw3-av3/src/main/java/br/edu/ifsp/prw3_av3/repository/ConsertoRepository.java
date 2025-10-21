package br.edu.ifsp.prw3_av3.repository;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    // GET completo paginado (parte 2) — versão apenas ativos:
    Page<Conserto> findAllByAtivoTrue(Pageable paginacao);

    // GET parcial sem paginação (parte 2/3) — somente ativos
    List<Conserto> findByAtivoTrue();
}