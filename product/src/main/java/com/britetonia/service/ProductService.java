package com.britetonia.service;

import com.britetonia.Exceptions.ProductAlreadyExistsException;
import com.britetonia.Exceptions.ProductNotFoundException;
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
                .subcategory(productRequest.getSubcategory())
                .createdAt(LocalDateTime.now())
                .build();

        if(productRepository.existsByName(product.getName())){
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists");
        }

        return productRepository.save(product);
    }

    public Product readProduct (Long id){
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " Not found"));
    }

    public List<ProductResponse> readProducts (){
        List<Product> products = productRepository.findAll();

       return products.stream().map(this::mapToProductResponse).toList();
    }


    public ProductResponse updateProduct (Long id, ProductRequest productRequest){
       Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " Not found"));

//            product.setId(id);
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImages(productRequest.getImages());
            product.setSubcategory(productRequest.getSubcategory());
            product.setUpdatedAt(LocalDateTime.now());

            productRepository.save(product);

            return mapToProductResponse(product);
        }
    }

    public void deleteProduct (Long id){
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .images(product.getImages())
                .subcategory(product.getSubcategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
