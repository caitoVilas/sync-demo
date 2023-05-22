package com.pedidos.appsync.config;

import com.pedidos.appsync.exception.ServiceNoResponseException;
import com.pedidos.appsync.service.SyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduleConf {
    private final SyncService syncService;

    @Scheduled(cron = "${scheduler.intervall}")
    public void task(){
        try {
            syncService.getOrders();
        }catch (Exception e){
                log.error("**** fallo el servicio syncro porque un servicio no responde ****");
        }

    }
}
