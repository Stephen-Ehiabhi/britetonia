package com.britetonia.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private List<OrderLineItemsDTO> orderLineItemsDTO;
};