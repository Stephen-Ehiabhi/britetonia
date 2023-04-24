package com.britetonia.controller;

import com.britetonia.model.Order;
import com.britetonia.Exceptions.InternalServerErrorException;
import com.britetonia.dto.OrderRequest;
import com.britetonia.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest){

        return new ResponseEntity<>(orderService.placeAnOrder(orderRequest), HttpStatus.CREATED);
    }
}
