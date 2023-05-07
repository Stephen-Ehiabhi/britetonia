package com.britetonia.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String price;
    private String productId;
    private String name;
    private String deliveryDate;
    private String customerEmail;
};