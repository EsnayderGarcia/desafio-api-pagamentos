package com.desafio.apipagamento.entities;

public enum StatusPagamento {
    PENDENTE("Pendente"),
    PROCESSADO_SUCESSO("Processado com Sucesso"),
    PROCESSADO_FALHA("Processado com Falha");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
