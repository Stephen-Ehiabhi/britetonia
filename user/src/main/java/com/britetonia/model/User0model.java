package com.britetonia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User0model {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence" , allocationSize=25)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "role") //admin, customer
    private String role;
}

