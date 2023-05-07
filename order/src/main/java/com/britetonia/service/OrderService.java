package com.britetonia.service;

import com.britetonia.Exceptions.InternalServerErrorException;
import com.britetonia.Exceptions.OrderNotFoundException;
//import com.britetonia.StripeCRUD;
import com.britetonia.dto.InventoryResponse;
import com.britetonia.dto.OrderLineItemsDTO;
import com.britetonia.dto.OrderRequest;
import com.britetonia.model.Order;
import com.britetonia.model.OrderLineItems;
import com.britetonia.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
//    private final StripeCRUD stripeCRUD;
    private final WebClient webClient;

    public void placeAnOrder(OrderRequest orderRequest) {
        try {
            Order order = new Order();
            //generate random id
            order.setOrderNumber(UUID.randomUUID().toString());

            //update the orderlinetimes
            List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
                    .stream()
                    .map(this::mapToListDTO)
                    .toList();

            order.setOrderLineItems(orderLineItems);

            //get all the product names and store in a list
            List<String> productNames = orderRequest.getOrderLineItems()
                    .stream()
                    .map(OrderLineItemsDTO::getProductName)
                    .toList();

            //call inventory service, to check if item is in stock
            InventoryResponse[] inventoryResponsesArray = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .host("http://localhost")
                            .port("8082")
                            .path("/api/v1/inventory")
                            .queryParam("productName", productNames)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            assert inventoryResponsesArray != null;

            //check that all products are in stock if one is false, it'll throw, false.
            boolean allProductsInStock = Arrays
                    .stream(inventoryResponsesArray)
                    .allMatch(InventoryResponse::getIsInStock);

            //check if product is in inventory stock
            if (allProductsInStock) {
//                stripeCRUD.chargeCard(orderRequest);
                orderRepository.save(order);
            } else {
                throw new OrderNotFoundException("Product is no longer in stock");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Error: " + e.getMessage());
        }
    }

    private OrderLineItems mapToListDTO(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItems.builder()
                .productId(orderLineItemsDTO.getProductId())
                .quantity(orderLineItemsDTO.getQuantity())
                .price(orderLineItemsDTO.getPrice())
                .customerId(orderLineItemsDTO.getCustomerId())
                .productName(orderLineItemsDTO.getProductName())
                .build();
    }

    public String generateOrderId() {
        String id = "brite_order" + String.format("%07d", new Random().nextInt(10000000));
        return id;
    }
}
