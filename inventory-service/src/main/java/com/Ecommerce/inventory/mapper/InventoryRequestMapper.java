package com.Ecommerce.inventory.mapper;

import org.springframework.stereotype.Component;

import com.Ecommerce.inventory.dto.InventoryRequest;
import com.Ecommerce.inventory.dto.InventoryResponse;
import com.Ecommerce.inventory.entity.Inventory;

@Component
public class InventoryRequestMapper {
	
	
	public Inventory toEntity(InventoryRequest dto) { 
		
		return Inventory.builder()
				.skuCode(dto.getSkuCode())
				.quantity(dto.getQuantity()).build();

	}
	
	public InventoryResponse toResponseDto(Inventory inventory) {
		
		return new InventoryResponse(inventory.getSkuCode(),
				inventory.getQuantity()>0,
				inventory.getQuantity());
		
	}
	

}
