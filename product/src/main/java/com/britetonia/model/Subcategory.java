package com.britetonia.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subcategory {
    @Id
    @SequenceGenerator(
            name = "subcategory_sequence",
            sequenceName = "subcategory_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subcategory_sequence"
    )
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}

