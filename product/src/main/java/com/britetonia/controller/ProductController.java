package com.britetonia.controller;

import com.britetonia.dto.ProductRequest;
import com.britetonia.dto.ProductResponse;
import com.britetonia.model.Product;
import com.britetonia.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProductController(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductsController() {
        return new ResponseEntity<>(productService.readProducts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> getProductController(@PathVariable("id") long id) {
        Optional<Product> product = productService.readProduct(id);
       if(product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
           return new ResponseEntity<>(product,HttpStatus.OK);
       }
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProductController(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductController(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
