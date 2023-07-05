package com.desafio.apipagamento.utils;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private final List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public void adicionarErro(FieldMessage fieldMessage) {
        erros.add(fieldMessage);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }
}
