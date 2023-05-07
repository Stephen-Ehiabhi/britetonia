package com.britetonia.dto;

import com.britetonia.model.Address;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String token;
}
