package com.desafio.apipagamento.domain.dtos;

import com.desafio.apipagamento.domain.enums.MetodoPagamento;
import com.desafio.apipagamento.domain.entities.Pagamento;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovoPagamentoRequest {
    @NotBlank(message = "Campo obrigatório.")
    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo Obrigatório.")
    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    @NotNull(message = "Campo obrigatório.")
    @Positive(message = "Informe um valor maior que zero.")
    private BigDecimal valor;

    public NovoPagamentoRequest() {
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

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Pagamento toModel() {
        Pagamento pagamento = new Pagamento();
        pagamento.setCpfCnpj(cpfCnpj);
        pagamento.setMetodoPagamento(metodoPagamento);
        pagamento.setNumeroCartao(numeroCartao);
        pagamento.setValor(valor);

        return pagamento;
    }

    public boolean deveInformarNumeroDeCartao() {
        return metodoPagamento.deveInformarNumeroDeCartao() && (numeroCartao == null || numeroCartao.isBlank());
    }
}
