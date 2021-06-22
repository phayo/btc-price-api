package com.chukwuebuka.btcpricechecker.web.rest.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler extends RuntimeException{
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleGeneralException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Unexpected Error occurred";
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", false);
        responseBody.put("message", bodyOfResponse);
        LOG.error(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleBadRequestException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", false);
        responseBody.put("message", bodyOfResponse);
        LOG.error(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFoundException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("success", false);
        responseBody.put("message", bodyOfResponse);
        LOG.error(ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}

