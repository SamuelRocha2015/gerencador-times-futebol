package br.com.codenatio.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "resource conflict")
public class IdentificadorUtilizadoException extends RuntimeException{

    public IdentificadorUtilizadoException() {
    }

    public IdentificadorUtilizadoException(String s) {
        super(s);
    }

}
