package com.britetonia.service;

import com.britetonia.Exceptions.ProductAlreadyExistsException;
import com.britetonia.Exceptions.ProductNotFoundException;
import com.britetonia.StripeCRUD;
import com.britetonia.dto.ProductRequest;
import com.britetonia.dto.ProductResponse;
import com.britetonia.dto.SearchQueryDTO;
import com.britetonia.model.ProductModel;
import com.britetonia.repository.ProductRepository;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final StripeCRUD stripeCRUD;

    // security: add authentication & authorization routes using spring-security
    // to access specific routes like create update delete products
    // transactional annotations are also a security measure to enable that
    // services are responded to by reversing the transaction if it fails, to ensure data hygiene

    // add auth only admins can create
    public ProductModel createProduct(ProductRequest productRequest) throws StripeException {
        ProductModel product = ProductModel.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .images(productRequest.getImages())
                .subcategory(productRequest.getSubcategory())
                .createdAt(LocalDateTime.now())
                .build();


        String productID = stripeCRUD.addProduct(product);

        product.setProductId(productID);

        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists");
        }

        return productRepository.save(product);
    }

    //no auth - everyone can
    public ProductModel readProduct(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " Not found"));
    }

    //no auth - everyone can
    public List<ProductResponse> readProducts() {
        List<ProductModel> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    // todo:  search route
    // todo: test route
    public List<ProductResponse> searchProducts(SearchQueryDTO searchQueryDto) {
        List<ProductModel> searchedProducts = productRepository.search(
                searchQueryDto.getSubcategory() != null ? searchQueryDto.getSubcategory().getName() : null,
                searchQueryDto.getMinPrice(),
                searchQueryDto.getMaxPrice(),
                searchQueryDto.getKeyword()
        );

        return searchedProducts.stream().map(this::mapToProductResponse).toList();

    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws StripeException {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " Not found"));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImages(productRequest.getImages());
        product.setSubcategory(productRequest.getSubcategory());
        product.setUpdatedAt(LocalDateTime.now());

        // update in stripe
        stripeCRUD.update(id, product);

        productRepository.save(product);

        return mapToProductResponse(product);

    }

    public void deleteProduct(Long id) throws StripeException {
        stripeCRUD.delete(id);
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(ProductModel product) {
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
