package com.Ecommerce.payment.dto;

import com.Ecommerce.payment.enums.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusUpdateRequest {
	
	@NotNull
	private PaymentStatus paymentStatus;

}
