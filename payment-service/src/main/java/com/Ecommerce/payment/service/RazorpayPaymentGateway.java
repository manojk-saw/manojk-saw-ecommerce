package com.Ecommerce.payment.service;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.Ecommerce.payment.dto.GatewayOrderResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RazorpayPaymentGateway implements PaymentGateway{
	
	private final RazorpayClient razorpayClient;

	@Override
	public GatewayOrderResponse createOrder(String orderId, BigDecimal amount) {
		
		try {
		JSONObject options=new JSONObject();
		
		options.put("amount", amount.multiply((BigDecimal.valueOf(100))));
		options.put("currency","INR");
		options.put("orderReceipt",orderId);
		
	
		Order order=razorpayClient.orders.create(options);
		
		return new GatewayOrderResponse(
				order.get("id"),
				order.get("currency"),
				amount,
				order.get("status"),
				"RAZORPAY");
				
		
		
		}catch(RazorpayException e){
			
			throw new IllegalStateException("RazorpayPayment Failed");
		}	
		
	}
	

}
