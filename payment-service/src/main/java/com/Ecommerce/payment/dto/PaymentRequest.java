package com.Ecommerce.payment.dto;
import java.math.BigDecimal;

import com.Ecommerce.payment.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
		  @NotNull 
		  String orderId;
		  
		@Positive 
		private BigDecimal amount;
		
		@NotNull 
		private PaymentMethod paymentMethod;
}