package com.britetonia.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
