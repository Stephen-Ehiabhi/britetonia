package com.britetonia.dto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDTO {
    private String productId;
    private String customerId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
