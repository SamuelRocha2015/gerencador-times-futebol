package br.com.codenatio.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "capitao nao encontrado")
public class CapitaoNaoInformadoException extends RuntimeException {
}
