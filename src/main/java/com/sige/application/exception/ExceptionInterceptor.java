package com.sige.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler({CampoException.class})
    public final ResponseEntity<Object> handleAllExceptions(CampoException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getCampo(), ex.getMensagem(), ex.getDetalhes(), ex.getOperacao());
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LoginException.class})
    public final ResponseEntity<Object> handleAllExceptions(LoginException ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getCampo(), ex.getMensagem(), ex.getDetalhes(), ex.getOperacao());
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ExceptionSchema execao = new ExceptionSchema(ex.getClass().getName(), ex.getMessage(), ex.getCause().getMessage(), null);
        return new ResponseEntity(execao, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
