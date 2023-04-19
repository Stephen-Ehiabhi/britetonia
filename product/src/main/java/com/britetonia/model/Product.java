package com.britetonia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "product_id"
    )
    @SequenceGenerator(
            name = "product_id",
            sequenceName = "product_id"
    )
    private long id;
    private String name;
    private String description;
    private String price;
    private String[] images;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Date createdAt;
}
