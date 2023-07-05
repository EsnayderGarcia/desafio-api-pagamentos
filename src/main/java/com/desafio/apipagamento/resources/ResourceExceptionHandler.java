package com.desafio.apipagamento.resources;

import com.desafio.apipagamento.exceptions.OperacaoInvalidaException;
import com.desafio.apipagamento.exceptions.PagamentoNaoEncontradoException;
import com.desafio.apipagamento.utils.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(PagamentoNaoEncontradoException.class)
	public ResponseEntity<StandardError> pagamentoNaoEncontrado(HttpServletRequest req, PagamentoNaoEncontradoException ex) {
		StandardError error = new StandardError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setErro(ex.getMessage());
		error.setPath(req.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(OperacaoInvalidaException.class)
	public ResponseEntity<StandardError> operacaoInvalida(HttpServletRequest req, OperacaoInvalidaException ex) {
		StandardError error = new StandardError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErro(ex.getMessage());
		error.setPath(req.getRequestURI());
		
		return ResponseEntity.badRequest().body(error);
	}
}
