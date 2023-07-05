package com.desafio.apipagamento.repositories;

import com.desafio.apipagamento.domain.entities.Pagamento;
import com.desafio.apipagamento.domain.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    @Query(
            "SELECT p FROM Pagamento p " +
            "WHERE (:cpfCnpj = '' OR p.cpfCnpj = :cpfCnpj) " +
            "AND (:status IS NULL OR p.status = :status)"
    )
    List<Pagamento> buscar(String cpfCnpj, StatusPagamento status);
}
