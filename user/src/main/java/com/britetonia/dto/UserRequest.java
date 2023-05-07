package com.britetonia.dto;

import com.britetonia.model.Address;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String role;
}
