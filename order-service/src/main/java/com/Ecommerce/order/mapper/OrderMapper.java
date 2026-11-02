package com.Ecommerce.order.mapper;

import org.springframework.stereotype.Component;

import com.Ecommerce.order.dto.OrderRequestDto;
import com.Ecommerce.order.dto.OrderResponseDto;
import com.Ecommerce.order.entity.Order;

@Component
public class OrderMapper {
	
	public Order toEntity(OrderRequestDto dto) {
		
		return Order.builder()
		.skuCode(dto.getSkuCode())
		.price(dto.getPrice())
		.quantity(dto.getQuantity()).build();
		
		
	}
	
	public OrderResponseDto orderResponse(Order order) {
		
		return OrderResponseDto.builder()
		.orderNumber(order.getOrderNumber())
		.orderStatus(order.getOrderStatus()).build();
	}

}
