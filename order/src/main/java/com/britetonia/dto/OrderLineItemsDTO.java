package com.britetonia.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
