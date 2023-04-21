package com.britetonia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
