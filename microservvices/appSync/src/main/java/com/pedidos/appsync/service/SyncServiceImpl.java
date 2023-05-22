package com.pedidos.appsync.service;

import com.pedidos.appsync.enums.OrderStatus;
import com.pedidos.appsync.exception.OrdersEmptyException;
import com.pedidos.appsync.exception.ServiceNoResponseException;
import com.pedidos.appsync.feignclients.OrderFeingClient;
import com.pedidos.appsync.feignclients.WorkOrderFeing;
import com.pedidos.appsync.model.Order;
import com.pedidos.appsync.model.OrderList;
import com.pedidos.appsync.model.WorkOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SyncServiceImpl implements SyncService {
    private final RestTemplate restTemplate;
    private final OrderFeingClient feingClient;
    private final WorkOrderFeing workOrderFeing;
    @Value("${url.pedidos}")
    private String urlPedidos;
    @Value("${url.work-orders}")
    private String urlOrdenesTrabajo;

    @Override
    public List<Order> getOrders() {
        log.info("incio servicio obtener pedidos");
        try {
            log.info("llamando servicio externo obtener pedidos: {}", urlPedidos);
            List<Order> orders = feingClient.getOrders();

            if (orders.isEmpty()){
                log.error("no se encontraron ordenes para proceasr");
                throw new OrdersEmptyException("no se encontraron ordenes para proceasr");
            }
            List<WorkOrder> workOrders = this.procesWorkOrders(orders);
            log.info("construida lista de ordenes de trabajo");
            List<Order> updatedOrders  = this.updateStatus(orders);
            log.info("pedidos actualizados");
            OrderList orderList = OrderList.builder()
                    .orders(updatedOrders)
                    .build();

            try {
                workOrderFeing.prueba();
            }catch (ResourceAccessException e){
                log.info("**** el servicio de ordenes de trabajo no responde *****");
                throw new ServiceNoResponseException("el servicio de ordenes de trabajo no responde");
            }catch (Exception e){
                log.info("**** el servicio de ordenes de trabajo no responde *****");
                throw new ServiceNoResponseException("el servicio de ordenes de trabajo no responde");
            }
            List<Order> savedOrders = this.updateOrders(orderList);
            log.info("se actualizo el estado de ordenes procesadas");
            log.info("guardando ordenes de trabajo...");
            List<WorkOrder> savedWorkOrders = this.createWorkOrders(workOrders);
            log.info("ordenes de trabajo guardadas");
            log.info("*** finalizado proceso de sincronizacion ****");
            return savedOrders;

        }catch (ResourceAccessException e){
            log.error("el servicio de pedidos no responde");
        }catch (Exception e){
            log.error("**** el servicio de pedidos no responde ****");
            throw new ServiceNoResponseException("el servicio de pedidos no responde");
        }

        return null;
    }

    private List<WorkOrder> procesWorkOrders(List<Order> orders){
        log.info("procesando pedidos obtenidos...");
        List<WorkOrder> workOrders = new ArrayList<>();
        log.info("construyendo ordenes de trabajo...");
        orders.forEach(order -> {
            WorkOrder workOrder = WorkOrder.builder()
                    .description(UUID.randomUUID().toString())
                    .date(LocalDateTime.now())
                    .order_id(order.getId())
                    .build();
            workOrders.add(workOrder);
        });

        return workOrders;
    }

    private List<Order> updateStatus(List<Order> orders){
        log.info("actualizando estando pedidos...");
        return  orders.stream().map(order -> {
            order.setStatus(OrderStatus.SYNC_COMPLETE);
            return order;
        }).collect(Collectors.toList());

    }

    private List<Order> updateOrders(OrderList orderList){
        try {
            log.info("llamando servicio externo actualizar estado de pedidos: {}", urlPedidos + "/update-status");
            List<Order> res = feingClient.updateStatus(orderList);
            //List<Order> resp = restTemplate.postForObject(url, orderList.getOrders(), List.class);
            return orderList.getOrders();
        }catch (ResourceAccessException e){
            log.error("el servicio de pedidos no responde");
        }catch (Exception e){
        log.error("**** el servicio de pedidos no responde ****");
        throw new ServiceNoResponseException("el servicio de pedidos no responde");
    }
        return orderList.getOrders();
    }

    private List<WorkOrder> createWorkOrders(List<WorkOrder> workOrders){
        try {
            log.info("llamar servicio externo guardar ordenes de trabajo {}", urlOrdenesTrabajo);
            workOrderFeing.saveWorkOrder(workOrders);
        }catch (ResourceAccessException e){
            log.error("el servicio de ordenes de trabajo no responde");
        }catch (Exception e){
            log.error("**** el servicio de ordenes de trabajo no responde ****");
            throw new ServiceNoResponseException("el servicio de ordenes de trabajo no responde");
        }
        return workOrders;
    }
}
