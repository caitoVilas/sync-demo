package com.pedidos.appsync.feignclients;

import com.pedidos.appsync.model.WorkOrder;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "app-work-order", url = "${url.work-orders}")
public interface WorkOrderFeing {
    @PostMapping
    ResponseEntity<?> saveWorkOrder(@RequestBody List<WorkOrder> workOrders);
}
