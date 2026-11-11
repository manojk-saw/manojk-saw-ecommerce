package com.Ecommerce.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.payment.dto.PaymentRequest;
import com.Ecommerce.payment.dto.PaymentResponse;
import com.Ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.Ecommerce.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
	
	
	private final PaymentService paymentService;
	
	
	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment( @RequestBody @Valid PaymentRequest request){
		
		PaymentResponse response=paymentService.createPayment(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable("orderId") String orderId){
		
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentByOrderId(orderId));        
	}
	
	@PostMapping("/{paymentId}")
	public ResponseEntity<PaymentResponse>updatePaymentStatus(@PathVariable("paymentId") Long paymentId, @RequestBody @Valid PaymentStatusUpdateRequest request){
		
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.updatPaymentStatusRequest(paymentId, request));
			
	}
	
	

}
