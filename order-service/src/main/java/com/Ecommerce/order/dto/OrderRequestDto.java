package com.Ecommerce.order.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
	
	private String skuCode;
	@Min(value=1, message="Quantity must be atleast 1")
	private Integer quantity;
	@NotNull(message="pice is mandatory")
	private BigDecimal price;
	

}
