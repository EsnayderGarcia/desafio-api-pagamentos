package com.desafio.apipagamento.entities;

import com.desafio.apipagamento.exceptions.OperacaoInvalidaException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoDebito;

    @NotBlank(message = "O campo é obrigatório.")
    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status = StatusPagamento.PENDENTE;

    private String numeroCartao;

    private BigDecimal valor;

    @CreationTimestamp
    private Instant instanteCriacao;

    @UpdateTimestamp
    private Instant instanteAtualizacao;

    public Pagamento() {
    }

    public Integer getCodigoDebito() {
        return codigoDebito;
    }

    public void setCodigoDebito(Integer codigoDebito) {
        this.codigoDebito = codigoDebito;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = metodoPagamento.deveInformarNumeroDeCartao() ? numeroCartao : null;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Instant getInstanteCriacao() {
        return instanteCriacao;
    }

    public void setInstanteCriacao(Instant instanteCriacao) {
        this.instanteCriacao = instanteCriacao;
    }

    public Instant getInstanteAtualizacao() {
        return instanteAtualizacao;
    }

    public void setInstanteAtualizacao(Instant instanteAtualizacao) {
        this.instanteAtualizacao = instanteAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(codigoDebito, pagamento.codigoDebito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoDebito);
    }
}
