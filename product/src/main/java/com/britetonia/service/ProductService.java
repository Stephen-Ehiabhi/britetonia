package com.britetonia.service;

import com.britetonia.dto.ProductRequest;
import com.britetonia.dto.ProductResponse;
import com.britetonia.model.Product;
import com.britetonia.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct (ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .images(productRequest.getImages())
                .createdAt(LocalDateTime.now())
//                .subcategory(productRequest.getSubcategoryId())
                .build();

       return productRepository.save(product);
    }

    public Optional<Product> readProduct (Long id){
        return productRepository.findById(id);
    }

    public List<ProductResponse> readProducts (){
        List<Product> products = productRepository.findAll();

       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .images(product.getImages())
//                .subcategoryId(product.getSubcategoryId())
                .build();
    }

    public Product updateProduct (Long id, ProductRequest productRequest){
        if(!productRepository.existsById(id)) {

        }

            Product product = Product.builder()
                    .id(id)
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .images(productRequest.getImages())
//                    .subcategory(productRequest.getSubcategoryId())
                    .createdAt(LocalDateTime.now())
                    .build();

        return productRepository.save(product);
    }

    public void deleteProduct (Long id){
        productRepository.deleteById(id);
    }

}
