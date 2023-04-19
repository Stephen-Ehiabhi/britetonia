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
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "category_id"
    )
    @SequenceGenerator(
            name = "category_id",
            sequenceName = "category_id"
    )
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "subcategory_Id")
    private Subcategory subcategory;
}
