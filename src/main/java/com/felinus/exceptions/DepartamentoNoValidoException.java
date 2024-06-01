package com.felinus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartamentoNoValidoException extends  RuntimeException{
    public DepartamentoNoValidoException(String mensaje){
        super(mensaje);
    }
}
