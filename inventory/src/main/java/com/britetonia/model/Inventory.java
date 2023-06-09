package com.britetonia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_inventory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_generator")
    @SequenceGenerator(name = "inventory_generator", sequenceName = "inventory_sequence")
    @Column(name = "id", insertable=false, updatable=false)
    private Long id;

    @Column(name = "productName", nullable = false)
    private String productName;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
