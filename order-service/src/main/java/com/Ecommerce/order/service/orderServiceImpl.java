package com.Ecommerce.order.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Ecommerce.order.client.InventoryFeignClient;
import com.Ecommerce.order.dto.InventoryResponse;
import com.Ecommerce.order.dto.OrderRequestDto;
import com.Ecommerce.order.dto.OrderResponseDto;
import com.Ecommerce.order.entity.Order;
import com.Ecommerce.order.event.OrderPlacedEvent;
import com.Ecommerce.order.kafka.OrderEventProducer;
import com.Ecommerce.order.mapper.OrderMapper;
import com.Ecommerce.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class orderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final InventoryFeignClient inventoryFeignClient;
	private final OrderEventProducer orderEventProduce;

	@Override
	public OrderResponseDto placeOrder(OrderRequestDto request) {
	
		System.out.println("skuCode: "+request.getSkuCode());
		InventoryResponse inventoryResponse=inventoryFeignClient.stockCheck(request.getSkuCode());
		
		boolean availibility=inventoryResponse.isInStock();
		Integer quantity=inventoryResponse.getAvailableQuantity();
		System.out.println("availableQuantity is: "+quantity);
		
		if(!availibility) {	
			throw new RuntimeException("sorry product is out of stock");	
		}
		
		String orderId=UUID.randomUUID().toString();
		Order order=orderMapper.toEntity(request);
		order.setOrderNumber(orderId);
		order.setOrderStatus("Order placed successfully");
		
		OrderPlacedEvent event=new OrderPlacedEvent();
		event.setEventId(UUID.randomUUID().toString());
		event.setOrderId(orderId);
		event.setQuantity(request.getQuantity());
		event.setEventId(request.getSkuCode());
		event.setEventTime(LocalDateTime.now());
		orderEventProduce.sendOrderEvent(event);
		
		return orderMapper.orderResponse(orderRepository.save(order));
		
		
	}

}
