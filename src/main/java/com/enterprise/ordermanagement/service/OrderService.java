package com.enterprise.ordermanagement.service;

import com.enterprise.ordermanagement.dto.OrderRequest;
import com.enterprise.ordermanagement.dto.OrderResponse;
import com.enterprise.ordermanagement.dto.orderUpdateRequest;
import com.enterprise.ordermanagement.entity.Order;
import com.enterprise.ordermanagement.exception.OrderNotFoundException;
import com.enterprise.ordermanagement.repository.OrderRepository;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {

        Order order = new Order();

        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return mapToResponse(savedOrder);
    }
    
    //1. create a method whose return type is like reponseorder bcz at the end we have return the response.
    //2. parameters of the method would be the id of the  order jisko update krna hai and request which is updated come formteh client throug reponse .
    //3. now first fetch the existing order using the specific order id 
    //4. now set the value from the update request and now cretaed a new updated order and save in the dB
    //5. and map it to the reposeorder through the maptoreponse method.
    public OrderResponse updateOrder(Long id, orderUpdateRequest request) {
    	Order order = orderRepository.findById(id)
    			.orElseThrow(()-> new OrderNotFoundException("ordernot found :" + id ));
    	
    	order.setProductId(request.getProductId());
    	order.setQuantity(request.getQuantity());
    	
    	Order updatedOrder = orderRepository.save(order);
    	
    	return mapToResponse(updatedOrder);
    }

    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found: " + id));

        return mapToResponse(order);
    }

    public Page<OrderResponse> getAllOrders(Pageable pageable) {

        return orderRepository.findAll(pageable)
                .map(this::mapToResponse);
                
    }
    
    public void deleteOrder(Long id) {
    	Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("order not found at this id :"+ id));
    	
    	orderRepository.delete(order);
    }

    private OrderResponse mapToResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getQuantity(),
                order.getCreatedAt(),
                order.getStatus()
        );
    }

}
