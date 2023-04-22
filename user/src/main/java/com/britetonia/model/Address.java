package com.britetonia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state_or_province", nullable = false)
    private String stateOrProvince;
    @Column(name = "postal_code", nullable = false)
    private String postal_code;
    @Column(name = "country", nullable = false)
    private String country;
}
