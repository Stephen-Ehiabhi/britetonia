package com.britetonia;

import com.britetonia.Exceptions.ProductNotFoundException;
import com.britetonia.model.ProductModel;
import com.britetonia.repository.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class StripeCRUD {
    private final String stripeKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
    private final ProductRepository productRepository;

    public String addProduct(ProductModel productData) throws StripeException {

        Stripe.apiKey = stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("description", productData.getDescription());
        params.put("name", productData.getName());

        Product product = Product.create(params);

        Map<String, Object> priceParams = new HashMap<>();
        priceParams.put("unit_amount", productData.getPrice());
        priceParams.put("currency", "PLN");
        priceParams.put("product", product.getId());

        //add the price to the product
//        Price.create(params);

        return product.getId();
    }

    public void update(long id, ProductModel productData) throws StripeException {
        Product product = Product.retrieve(getProductId(id));
        // Product price = Product.retrieve(getPriceId(id));

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", productData.getDescription());
        metadata.put("name", productData.getName());

        /* Map<String, Object> priceMetaData = new HashMap<>();
         metadata.put("unit_amount", productData.getPrice());
         price.update(priceMetaData); */
        product.update(metadata);
    }

    public void delete(long id) throws StripeException {
        Stripe.apiKey = stripeKey;

        Product product = Product.retrieve(getProductId(id));
        Product price = Product.retrieve(getPriceId(id));

        price.delete();
        product.delete();
    }

    //reusable functions
    private String getProductId(long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("User with ID " + id + " not found"));

        return productModel.getProductId();
    }

    private String getPriceId(long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("User with ID " + id + " not found"));

        return productModel.getPriceId();
    }
}