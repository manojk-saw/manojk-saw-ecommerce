package com.Ecommerce.inventory.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.Ecommerce.inventory.Exception.InventoryNotFoundException;
import com.Ecommerce.inventory.Repository.InventoryRepository;
import com.Ecommerce.inventory.Repository.ProcessedOrderRepository;
import com.Ecommerce.inventory.dto.InventoryRequest;
import com.Ecommerce.inventory.dto.InventoryResponse;
import com.Ecommerce.inventory.entity.Inventory;
import com.Ecommerce.inventory.entity.ProcessedOrder;
import com.Ecommerce.inventory.event.OrderPlacedEvent;
import com.Ecommerce.inventory.mapper.InventoryRequestMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepo;
	private final InventoryRequestMapper inventoryMapper;
	private final ProcessedOrderRepository processedOrderRepo;
	
	
	public InventoryResponse checkInventory(String skuCode) {
		Inventory inventory=inventoryRepo.findBySkuCode(skuCode).orElseThrow(()->new InventoryNotFoundException(skuCode));
		return inventoryMapper.toResponseDto(inventory);
		
	}
	
	
	public void addInventory(InventoryRequest dto) {
		
		Inventory inventory =inventoryMapper.toEntity(dto);
		inventoryRepo.save(inventory);

		
	}
	
	
	@Transactional
	public void updateStock(OrderPlacedEvent event) {
		
		if(processedOrderRepo.existsByOrderId(event.getOrderId())) {
			
			return;
		}
		
		Inventory inventory=inventoryRepo.findBySkuCode(event.getSkuCode())
				.orElseThrow(()->new InventoryNotFoundException("Inventory not found with the give skuCode: "+event.getSkuCode()));
		
		Integer currentQuantity=inventory.getQuantity();
		Integer orderedQuantity=event.getQuantity();
		Integer remainingQuantity=currentQuantity-orderedQuantity;
		
		inventory.setQuantity(remainingQuantity);
		inventoryRepo.save(inventory);
		
		ProcessedOrder processed=new ProcessedOrder();
		processed.setOrderId(event.getOrderId());
		processed.setProcessedAt(LocalDateTime.now());
		
		processedOrderRepo.save(processed);
		
	}
	
	
}
