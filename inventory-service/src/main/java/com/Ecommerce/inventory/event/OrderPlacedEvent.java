package com.Ecommerce.inventory.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderPlacedEvent {
	
	private String eventId;
	private String orderId;
	private Integer quantity;
	private String skuCode;
	private LocalDateTime eventTime;

}
