package com.Ecommerce.order.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.Ecommerce.order.event.OrderPlacedEvent;


@Service
public class OrderEventProducer {
	

	private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
	
	@Autowired
	public OrderEventProducer(KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate) {
		
		this.kafkaTemplate=kafkaTemplate;
		
	}
	
	public void sendOrderEvent(OrderPlacedEvent event) {
		
		kafkaTemplate.send("order-event", event.getOrderId(), event);
	}

}
