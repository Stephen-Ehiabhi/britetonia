package com.britetonia.dto;

import com.britetonia.model.Address;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Address address;
    private String role;
}
