package com.pedidos.workorder.model;

import com.pedidos.workorder.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Long id;
    private String identifier;
    private LocalDateTime date;
    private OrderStatus status;
}
