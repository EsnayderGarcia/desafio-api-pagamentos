package com.desafio.apipagamento.domain.dtos;

import com.desafio.apipagamento.domain.entities.Pagamento;
import com.desafio.apipagamento.domain.enums.MetodoPagamento;
import com.desafio.apipagamento.validations.EnumValidation;
import org.springframework.util.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovoPagamentoRequest {
    @NotBlank(message = "Campo CPF/CNPJ é obrigatório.")
    private String cpfCnpj;

    @EnumValidation(message = "Informe um método de pagamento válido.", enumClass = MetodoPagamento.class)
    private String metodoPagamento;

    private String numeroCartao;

    @NotNull(message = "Campo valor é obrigatório.")
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

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
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
        pagamento.setMetodoPagamento(MetodoPagamento.valueOf(metodoPagamento));
        pagamento.setNumeroCartao(numeroCartao);
        pagamento.setValor(valor);

        return pagamento;
    }

    public boolean deveInformarNumeroDeCartao() {
        MetodoPagamento tipoPagamento = MetodoPagamento.valueOf(metodoPagamento);
        return tipoPagamento.deveInformarNumeroDeCartao() && !StringUtils.hasText(numeroCartao);
    }
}
