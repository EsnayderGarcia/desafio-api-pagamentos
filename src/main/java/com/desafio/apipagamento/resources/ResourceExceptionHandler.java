package com.desafio.apipagamento.resources;

import com.desafio.apipagamento.exceptions.OperacaoInvalidaException;
import com.desafio.apipagamento.exceptions.PagamentoNaoEncontradoException;
import com.desafio.apipagamento.utils.FieldMessage;
import com.desafio.apipagamento.utils.StandardError;
import com.desafio.apipagamento.utils.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> atributosInvalidos(HttpServletRequest req, MethodArgumentNotValidException ex) {
		ValidationError error = new ValidationError();

		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setErro("Um ou mais campos estão inválidos.");
		error.setPath(req.getRequestURI());

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> error.adicionarErro(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage())));

		return ResponseEntity.unprocessableEntity().body(error);
	}
}
