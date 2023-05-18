package com.pedidos.workorder.entitty;

import com.pedidos.workorder.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "works_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Long orderId;
    private LocalDateTime date;
}
