package com.pedidos.appsync.config;

import com.pedidos.appsync.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConf {
    private final SyncService syncService;

    @Scheduled(cron = "${scheduler.intervall}")
    public void task(){
        syncService.getOrders();
    }
}
