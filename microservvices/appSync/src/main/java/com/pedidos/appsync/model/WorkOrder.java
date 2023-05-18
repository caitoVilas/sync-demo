package com.pedidos.appsync.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkOrder {
    private Long id;
    private String description;
    private Long order_id;
    private LocalDateTime date;
}
