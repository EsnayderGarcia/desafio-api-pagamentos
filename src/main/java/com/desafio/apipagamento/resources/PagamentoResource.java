package com.desafio.apipagamento.resources;

import com.desafio.apipagamento.domain.dtos.NovoPagamentoRequest;
import com.desafio.apipagamento.domain.dtos.NovoStatusPagamento;
import com.desafio.apipagamento.domain.dtos.PagamentoResponse;
import com.desafio.apipagamento.services.PagamentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("pagamentos")
public class PagamentoResource {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar um pagamento")
    public PagamentoResponse receber(@RequestBody @Valid NovoPagamentoRequest novoPagamento) {
        return pagamentoService.receber(novoPagamento);
    }

    @PutMapping("/{codigoDebito}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualizar o status de um pagamento")
    public PagamentoResponse atualizarStatus(@RequestBody @Valid NovoStatusPagamento novoStatusPagamento,
                                             @PathVariable @ApiParam(value = "Código do débito") Integer codigoDebito) {
        return pagamentoService.atualizarStatus(novoStatusPagamento, codigoDebito);
    }

    @DeleteMapping("/{codigoDebito}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Remover um pagamento")
    public void deletar(@PathVariable @ApiParam(value = "Código do débito") Integer codigoDebito) {
        pagamentoService.deletar(codigoDebito);
    }

    @GetMapping("/{codigoDebito}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Buscar pagamento por código do débito")
    public PagamentoResponse buscarPorCodigoDebito(@PathVariable @ApiParam(value = "Código do débito") Integer codigoDebito) {
        return pagamentoService.buscarPorCodigoDebito(codigoDebito);
    }
}
