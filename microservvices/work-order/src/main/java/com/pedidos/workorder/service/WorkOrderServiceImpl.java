package com.pedidos.workorder.service;

import com.pedidos.workorder.entitty.WorkOrder;
import com.pedidos.workorder.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService{
    private final WorkOrderRepository workOrderRepository;


    @Override
    public void createWorkOrders(List<WorkOrder> workOrders) {
        log.info("servicio guardar ordenes de trabajo");
        workOrderRepository.saveAll(workOrders);
    }
}
