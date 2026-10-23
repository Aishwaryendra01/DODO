package com.enterprise.ordermanagement.controller;

import com.enterprise.ordermanagement.dto.OrderRequest;
import com.enterprise.ordermanagement.dto.OrderResponse;
import com.enterprise.ordermanagement.dto.orderUpdateRequest;
import com.enterprise.ordermanagement.service.EmailService;
import com.enterprise.ordermanagement.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    
    private final EmailService emailService;

//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
    

    @PostMapping
    public ResponseEntity<OrderResponse> createorder(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.createOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    public OrderController(OrderService orderService, EmailService emailService) {
	super();
	this.orderService = orderService;
	this.emailService = emailService;
}

	@GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {

        return ResponseEntity.ok(
                orderService.getAllOrders(pageable)
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder( 
    		@PathVariable Long id, 
    		@Valid @RequestBody orderUpdateRequest request ){
    	return ResponseEntity.ok(orderService.updateOrder(id, request));	
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id ){
    	
    	orderService.deleteOrder(id);
    	
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PostMapping("/send")
    public String sendEmail() {

        return emailService.sendEmail(
                "a.usepurpose@gmail.com",
                "use2purpose@gmail.com",
                "Order Service Test",
                "Hello! This email was sent from my Spring Boot application."
        );
    }
}