package com.desafio.apipagamento.dtos;

import com.desafio.apipagamento.entities.StatusPagamento;

import javax.validation.constraints.NotNull;

public class NovoStatusPagamento {
    @NotNull(message = "Campo obrigatório.")
    private StatusPagamento status;

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
