package com.britetonia.service;

import com.britetonia.model.Order;
import com.britetonia.Exceptions.InternalServerErrorException;
import com.britetonia.Exceptions.OrderAlreadyExistsException;
import com.britetonia.dto.OrderLineItemsDTO;
import com.britetonia.dto.OrderRequest;
import com.britetonia.model.OrderLineItems;
import com.britetonia.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public Order placeAnOrder(OrderRequest orderRequest) {
            Order order = new Order();
            order.setOrderNumber(generateId());

            List<OrderLineItems> orderLineItems = orderRequest
                       .getOrderLineItemsDTO()
                       .stream()
                       .map(this::mapToDto)
                       .toList();

            order.setOrderLineItems(orderLineItems);

//        if(orderRepository.existsById(orderLineItems.)){
//            throw new OrderAlreadyExistsException("User with id" + order.getId() + "not Found");
//        }else{
//
      log.info(String.valueOf(order));
//
      return orderRepository.save(order);
//            }
    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }

    //generate brite id for the order
    public String generateId() {
        String id = "brite" + String.format("%07d", new Random().nextInt(10000000));
        return id;
    }
}
