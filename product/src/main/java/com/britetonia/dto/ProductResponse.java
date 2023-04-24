package com.britetonia.dto;

import com.britetonia.model.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> images;
    private Subcategory subcategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
