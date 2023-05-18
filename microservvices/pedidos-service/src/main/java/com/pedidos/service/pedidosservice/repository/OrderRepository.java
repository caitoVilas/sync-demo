package com.pedidos.service.pedidosservice.repository;

import com.pedidos.service.pedidosservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //@Query("SELECT o FROM Order o WHERE o.status = 'READY' ORDER BY o.date ASC LIMIT 200")
    @Query(value = "SELECT * FROM orders  WHERE status = 'READY' ORDER BY date ASC LIMIT 200", nativeQuery = true)
    List<Order> findByStatus();
}
