package com.Ecommerce.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.payment.service.RazorpayWebhookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class WebhookController {
	
	private final RazorpayWebhookService webhookService;
	
	@PostMapping("/webhook/razorpay")
	public ResponseEntity<Void> handleWebhook(@RequestHeader("X-Razorpay Signature") String signature, @RequestBody String payload){
		
		webhookService.processWebhook(signature, payload);
		
		return ResponseEntity.ok().build();
	}

}
