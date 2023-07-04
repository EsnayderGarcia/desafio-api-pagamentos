package com.desafio.apipagamento.services;

import com.desafio.apipagamento.dtos.NovoPagamentoRequest;
import com.desafio.apipagamento.dtos.PagamentoResponse;
import com.desafio.apipagamento.entities.Pagamento;
import com.desafio.apipagamento.exceptions.OperacaoInvalidaException;
import com.desafio.apipagamento.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoResponse receber(NovoPagamentoRequest novoPagamento) {
        if (novoPagamento.deveInformarNumeroDeCartao())
            throw new OperacaoInvalidaException("O número do cartão é obrigatório.");

        Pagamento pagamento = pagamentoRepository.save(novoPagamento.toModel());
        return new PagamentoResponse(pagamento);
    }
}