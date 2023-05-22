package com.pedidos.appsync.exception;
public class ServiceNoResponseException extends RuntimeException{
    public ServiceNoResponseException(String msg){
        super(msg);
    }
}
