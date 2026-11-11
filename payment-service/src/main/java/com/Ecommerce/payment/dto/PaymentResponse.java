package com.Ecommerce.payment.dto;
import java.math.BigDecimal;

import com.Ecommerce.payment.enums.PaymentMethod;
import com.Ecommerce.payment.enums.PaymentStatus;

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
public class PaymentResponse {
		private Long paymentId;
		private String orderId;
		private BigDecimal amount;
		private PaymentStatus paymentStatus;
		private String transactionId;
		private PaymentMethod paymentMethod;
}
