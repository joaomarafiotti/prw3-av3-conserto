package br.edu.ifsp.prw3_av3.repository;

import br.edu.ifsp.prw3_av3.domain.conserto.Conserto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {}
