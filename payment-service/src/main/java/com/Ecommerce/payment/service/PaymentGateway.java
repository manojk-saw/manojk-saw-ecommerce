package com.Ecommerce.payment.service;

import java.math.BigDecimal;

import com.Ecommerce.payment.dto.GatewayOrderResponse;

public interface PaymentGateway {
	
	GatewayOrderResponse createOrder(String orderId, BigDecimal amount);

}
