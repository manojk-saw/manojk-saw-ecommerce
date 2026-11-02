package com.Ecommerce.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Ecommerce.order.client.InventoryFeignClient;
import com.Ecommerce.order.dto.InventoryResponse;
import com.Ecommerce.order.dto.OrderRequestDto;
import com.Ecommerce.order.dto.OrderResponseDto;
import com.Ecommerce.order.entity.Order;
import com.Ecommerce.order.mapper.OrderMapper;
import com.Ecommerce.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class orderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final InventoryFeignClient inventoryFeignClient;

	@Override
	public OrderResponseDto placeOrder(OrderRequestDto request) {
	
		InventoryResponse inventoryResponse=inventoryFeignClient.isInStock(request.getSkuCode());
		boolean isInStock=inventoryResponse.isInStock();
		Integer quantity=inventoryResponse.getAvailableQuantity();
		System.out.println("availableQuantity is: "+quantity);
		
		if(!isInStock) {	
			throw new RuntimeException("sorry product is out of stock");	
		}
		
		String orderId=UUID.randomUUID().toString();
		Order order=orderMapper.toEntity(request);
		order.setOrderNumber(orderId);
		order.setOrderStatus("Order placed successfully");
		
		return orderMapper.orderResponse(orderRepository.save(order));
		
		
	}

}
