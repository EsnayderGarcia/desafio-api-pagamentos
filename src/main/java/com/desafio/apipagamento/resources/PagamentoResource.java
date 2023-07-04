package com.desafio.apipagamento.resources;

import com.desafio.apipagamento.dtos.NovoPagamentoRequest;
import com.desafio.apipagamento.dtos.NovoStatusPagamento;
import com.desafio.apipagamento.dtos.PagamentoResponse;
import com.desafio.apipagamento.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("pagamentos")
public class PagamentoResource {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponse receber(@RequestBody @Valid NovoPagamentoRequest novoPagamento) {
        return pagamentoService.receber(novoPagamento);
    }

    @PutMapping("/{codigoDebito}")
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResponse atualizarStatus(@RequestBody @Valid NovoStatusPagamento novoStatusPagamento, @PathVariable Integer codigoDebito) {
        return pagamentoService.atualizarStatus(novoStatusPagamento, codigoDebito);
    }
}
