package com.britetonia.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
        private String productName;
        private Integer quality;
}
