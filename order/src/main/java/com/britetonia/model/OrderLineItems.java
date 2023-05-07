package com.britetonia.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_order_line_Items")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @SequenceGenerator(name = "order_line_sequence", sequenceName = "order_line_sequence" , allocationSize=25)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_sequence")
    private Long id;
    private String productId;
    private String customerId;
    private String productName;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    private LocalDateTime deliveryDate = LocalDate.now().minusDays(12).atStartOfDay();
    private Status status = Status.not_delivered;
}
