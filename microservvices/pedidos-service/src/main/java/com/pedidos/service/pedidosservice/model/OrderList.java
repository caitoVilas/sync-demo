package com.pedidos.service.pedidosservice.model;

import com.pedidos.service.pedidosservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderList {
    private List<Order> orders;
}
