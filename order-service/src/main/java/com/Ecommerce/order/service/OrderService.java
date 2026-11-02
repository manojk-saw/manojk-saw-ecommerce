package com.Ecommerce.order.service;

import com.Ecommerce.order.dto.OrderRequestDto;
import com.Ecommerce.order.dto.OrderResponseDto;


public interface OrderService {
	
	OrderResponseDto placeOrder(OrderRequestDto request);

}
