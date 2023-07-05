package com.desafio.apipagamento.exceptions;

public class PagamentoNaoEncontradoException extends RuntimeException {
    public PagamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
