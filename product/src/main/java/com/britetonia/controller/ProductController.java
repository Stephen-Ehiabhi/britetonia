package com.britetonia.controller;

import com.britetonia.dto.ProductRequest;
import com.britetonia.dto.ProductResponse;
import com.britetonia.dto.SearchQueryDTO;
import com.britetonia.model.ProductModel;
import com.britetonia.service.ProductService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> createProductController(@RequestBody ProductRequest productRequest) throws StripeException {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductsController() {
        return new ResponseEntity<>(productService.readProducts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductModel> getProductController(@PathVariable("id") long id) {
        ProductModel product = productService.readProduct(id);
           return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchController(@RequestBody SearchQueryDTO searchQueryDTO) {
        return new ResponseEntity<>(productService.searchProducts(searchQueryDTO), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProductController(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) throws StripeException {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductController(@PathVariable("id") long id) throws StripeException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
