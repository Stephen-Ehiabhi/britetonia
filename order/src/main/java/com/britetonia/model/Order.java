package com.britetonia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_order")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "order_t_sequence", sequenceName = "order_t_sequence" , allocationSize=25)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_t_sequence")
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
};