package com.britetonia.model;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "product_id_sequence"
    )
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )

    private Long productId;
    private String name;
    private String description;
    private String price;
    private List images;
    private String collectionName;
    Category categoryId;
    private Date createdAt;
}
