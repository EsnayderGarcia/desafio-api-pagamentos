package com.desafio.apipagamento.repositories;

import com.desafio.apipagamento.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
