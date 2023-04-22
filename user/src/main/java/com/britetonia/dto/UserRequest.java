package com.britetonia.dto;

import com.britetonia.model.Address;
import com.britetonia.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private long Id;
    private String name;
    private String email;
    private String phone;

//    private Address address;
//    private Role role;
}
