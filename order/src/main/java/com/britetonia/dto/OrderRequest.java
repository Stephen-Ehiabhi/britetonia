package com.britetonia.dto;

import com.britetonia.model.OrderLineItems;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDTO> orderLineItems;
//    private Card card;
};