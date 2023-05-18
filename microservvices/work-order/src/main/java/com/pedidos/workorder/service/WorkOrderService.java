package com.pedidos.workorder.service;

import com.pedidos.workorder.entitty.WorkOrder;

import java.util.List;

public interface WorkOrderService {
    void createWorkOrders(List<WorkOrder> workOrders);
}
