package com.Ecommerce.inventory.service;

import com.Ecommerce.inventory.Exception.InventoryNotFoundException;
import com.Ecommerce.inventory.Repository.InventoryRepository;
import com.Ecommerce.inventory.dto.InventoryRequest;
import com.Ecommerce.inventory.dto.InventoryResponse;
import com.Ecommerce.inventory.entity.Inventory;
import com.Ecommerce.inventory.mapper.InventoryRequestMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepo;
	private final InventoryRequestMapper inventoryMapper;
	
	
	public InventoryResponse checkInventory(String sku) {
		Inventory inventory=inventoryRepo.findBySkuCode(sku).orElseThrow(()->new InventoryNotFoundException(sku));
		return inventoryMapper.toResponseDto(inventory);
		
	}
	
	
	public void addInventory(InventoryRequest dto) {
		
		Inventory inventory =inventoryMapper.toEntity(dto);
		inventoryRepo.save(inventory);

		
	}
	
	
	
	

}
