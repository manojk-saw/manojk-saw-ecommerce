package com.Ecommerce.inventory.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.Ecommerce.inventory.event.OrderPlacedEvent;
import com.Ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
	
	
	private final InventoryService inventoryservice;
	
	
	@KafkaListener(topics="order-event",groupId="inventory-group")
	public void consumeOrderEvent(OrderPlacedEvent event) {
		
		inventoryservice.updateStock(event);
		
		
	}
	
	

}
