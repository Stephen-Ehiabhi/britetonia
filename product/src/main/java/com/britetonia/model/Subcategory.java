package com.britetonia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "subcategory_id"
    )
    @SequenceGenerator(
            name = "subcategory_id",
            sequenceName = "subcategory_id"
    )
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
