package com.britetonia.dto;

import com.britetonia.model.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchQueryDTO {
    private String keyword;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Subcategory subcategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
