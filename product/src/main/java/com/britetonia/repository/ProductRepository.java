package com.britetonia.repository;

import com.britetonia.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAll();
    Boolean existsByName(String name);
    @Query("SELECT p FROM Product p WHERE "
            + "(:subcategory IS NULL OR p.subcategory.name = :subcategory) "
            + "AND (:minPrice IS NULL OR p.price >= :minPrice) "
            + "AND (:maxPrice IS NULL OR p.price <= :maxPrice) "
            + "OR (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "OR (LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    List<ProductModel> search(@Param("subcategory") String subcategory,
                              @Param("minPrice") BigDecimal minPrice,
                              @Param("maxPrice") BigDecimal maxPrice,
                              @Param("keyword") String keyword);
}
