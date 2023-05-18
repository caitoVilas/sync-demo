package com.pedidos.appsync.exception;

public class OrdersEmptyException extends RuntimeException{
    public OrdersEmptyException(String msg){
        super(msg);
    }
}
