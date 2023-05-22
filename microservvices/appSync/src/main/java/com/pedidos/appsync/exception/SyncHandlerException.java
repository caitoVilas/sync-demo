package com.pedidos.appsync.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class SyncHandlerException {

    @ExceptionHandler(OrdersEmptyException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ErrorDTO> ordersEmptyException(Exception e, HttpServletRequest request){
        ErrorDTO response = ErrorDTO.builder()
                .code(204)
                .message(e.getMessage())
                .timestemp(LocalDateTime.now())
                .build();
        return new ResponseEntity(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ServiceNoResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> serviceNoResponseException(Exception e, HttpServletRequest request){
        ErrorDTO response = ErrorDTO.builder()
                .code(503)
                .message(e.getMessage())
                .timestemp(LocalDateTime.now())
                .build();
        return new  ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
