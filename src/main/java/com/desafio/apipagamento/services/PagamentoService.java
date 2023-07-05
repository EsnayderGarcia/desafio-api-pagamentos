package com.desafio.apipagamento.services;

import com.desafio.apipagamento.domain.dtos.NovoPagamentoRequest;
import com.desafio.apipagamento.domain.dtos.NovoStatusPagamento;
import com.desafio.apipagamento.domain.dtos.PagamentoResponse;
import com.desafio.apipagamento.domain.entities.Pagamento;
import com.desafio.apipagamento.domain.enums.StatusPagamento;
import com.desafio.apipagamento.exceptions.OperacaoInvalidaException;
import com.desafio.apipagamento.exceptions.PagamentoNaoEncontradoException;
import com.desafio.apipagamento.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoResponse receber(NovoPagamentoRequest novoPagamento) {
        if (novoPagamento.deveInformarNumeroDeCartao())
            throw new OperacaoInvalidaException("O número do cartão é obrigatório para pagamentos no débito ou no crédito.");

        Pagamento pagamento = pagamentoRepository.save(novoPagamento.toModel());
        return new PagamentoResponse(pagamento);
    }

    @Transactional
    public PagamentoResponse atualizarStatus(NovoStatusPagamento novoStatusPagamento, Integer codigoDebito) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento de código "+codigoDebito+" não encontrado para atualização de status."));

        pagamento.getStatus().validarAtualizacaoStatus(novoStatusPagamento.getStatus());
        pagamento.setStatus(novoStatusPagamento.getStatus());

        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoResponse(pagamento);
    }

    @Transactional
    public void deletar(Integer codigoDebito) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento de código "+codigoDebito+" não encontrado para exclusão."));

        pagamento.validarExclusao();
        pagamentoRepository.delete(pagamento);
    }

    @Transactional(readOnly = true)
    public PagamentoResponse buscarPorCodigoDebito(Integer codigoDebito) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento de código "+codigoDebito+" não encontrado."));

        return new PagamentoResponse(pagamento);
    }

    @Transactional(readOnly = true)
    public List<PagamentoResponse> buscar(String cpfCnpj, StatusPagamento statusPagamento) {
            return pagamentoRepository.buscar(cpfCnpj.trim(), statusPagamento)
                    .stream()
                    .map(PagamentoResponse::new)
                    .collect(Collectors.toList());
    }
}
