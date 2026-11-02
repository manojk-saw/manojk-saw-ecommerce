package com.Ecommerce.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryRequest {

	
	@NotBlank(message="SKU code must not be blank")
	private String skuCode;
	
	@Min(value=0, message="Minimum quantity must be 0 or greager")
	private int quantity;
 	
}
