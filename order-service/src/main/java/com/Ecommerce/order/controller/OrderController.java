package com.Ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.order.dto.OrderRequestDto;
import com.Ecommerce.order.dto.OrderResponseDto;
import com.Ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDto dto){
		
	     OrderResponseDto orderPlaced=orderService.placeOrder(dto);
	     
	     return ResponseEntity.status(HttpStatus.CREATED).body(orderPlaced);			
		
	}

}
