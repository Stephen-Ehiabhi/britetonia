package com.britetonia.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String addressLine;
    private String city;
    private String stateOrProvince;
    private String postal_code;
    private String country;
}
