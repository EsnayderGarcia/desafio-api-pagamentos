package com.desafio.apipagamento.domain.dtos;

import com.desafio.apipagamento.domain.enums.StatusPagamento;

import javax.validation.constraints.NotNull;

public class NovoStatusPagamento {
    @NotNull(message = "Campo status do pagamento é obrigatório.")
    private StatusPagamento status;

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
