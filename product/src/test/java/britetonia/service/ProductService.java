package britetonia.service;

import britetonia.dto.ProductRequest;
import britetonia.dto.ProductResponse;
import britetonia.model.Product;
import britetonia.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct (ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .images(productRequest.getImages())
//                .subcategory(productRequest.getSubcategoryId())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

//    public Optional<ProductResponse> readProduct (Long id){
//       return productRepository.findById(id);
//    }

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

    public void updateProduct (Long id, ProductRequest productRequest){
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .images(productRequest.getImages())
//                    .subcategory(productRequest.getSubcategoryId())
                    .createdAt(LocalDateTime.now())
                    .build();

            productRepository.save(product);
            log.info("product updated successfully");
    }

    public void deleteProduct (Long id){
        productRepository.deleteById(id);
    }

}
