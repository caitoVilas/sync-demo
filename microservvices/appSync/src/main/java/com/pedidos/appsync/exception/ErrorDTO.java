package com.pedidos.appsync.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDTO {
    private Integer code;
    private String message;
    private LocalDateTime timestemp;
}
