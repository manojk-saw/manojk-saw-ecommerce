package com.Ecommerce.payment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayOrderResponse {
	
	private String gatewayOrderId;
	private String  currency;
	private BigDecimal amount;
	private String status;
	private String gatewayName;
	

}
