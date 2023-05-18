package com.pedidos.appsync.controller;

import com.pedidos.appsync.model.Order;
import com.pedidos.appsync.service.SyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sync")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Syncronizacion de pedidos")
public class SyncController {
    private final SyncService syncService;

    @GetMapping
    @Operation(description = "sincronizacion de pedidos", summary = "sincronizacion de pedidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<List<Order>> getOrders(){
        log.info("--- endpoint sync obtener pedidos ---");
        return ResponseEntity.ok(syncService.getOrders());
    }
}
