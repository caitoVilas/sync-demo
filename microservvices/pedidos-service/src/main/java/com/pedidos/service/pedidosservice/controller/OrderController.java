package com.pedidos.service.pedidosservice.controller;

import com.pedidos.service.pedidosservice.entity.Order;
import com.pedidos.service.pedidosservice.model.OrderList;
import com.pedidos.service.pedidosservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "servicio de pedidos")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(description = "crea 1000 registros nuevos en la tabla orders",
    summary = "crea 1000 registros nuevos en la tabla orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<?> createOrders(){
        log.info("--- endpoint pedido crear pedidos ---");
        orderService.saveOrders();
        return ResponseEntity.ok(null);
    }

    @GetMapping
    @Operation(description = "consulta un conjunto de pedidos", summary = "consulta un conjunto de pedidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public List<Order> getOrders(){
        log.info("--- endpoint pedidos obtener pedidos ---");
        return orderService.getOrders();
    }

    @PostMapping("/update")
    public List<Order> updateStatus(@RequestBody OrderList orderList){
        log.info("--- endpoint actualizacion de estado de pedidos procesados ---");
        orderService.updateOrders(orderList.getOrders());
        return null;
    }
}
