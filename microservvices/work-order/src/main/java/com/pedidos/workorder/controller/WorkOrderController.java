package com.pedidos.workorder.controller;

import com.pedidos.workorder.entitty.WorkOrder;
import com.pedidos.workorder.service.WorkOrderService;
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
@RequestMapping("/api/works-orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "servicio - ordenes de trabajo")
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    @PostMapping
    @Operation(description = "guardar ordenes de trabajo", summary = "guardar ordenes de trabajo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<?> saveWorkOrder(@RequestBody List<WorkOrder> workOrders){
        log.info("--- endpoint guardar ordenes de trabajo ---");
        workOrderService.createWorkOrders(workOrders);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    @Operation(description = "endpoint de prueba", summary = "endpoint de prueba")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<String> prueba(){
        log.info("--- endpoint prueba servicio ordenes de trabajo ---");
        return ResponseEntity.ok("ok");
    }
}
