package br.com.codenatio.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "time not found")
public class JogadorNaoEncontradoException extends RuntimeException {
}
