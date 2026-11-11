package com.Ecommerce.payment.service;

import org.springframework.stereotype.Service;

import com.Ecommerce.payment.dto.PaymentRequest;
import com.Ecommerce.payment.dto.PaymentResponse;
import com.Ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.Ecommerce.payment.entity.Payment;
import com.Ecommerce.payment.enums.PaymentMethod;
import com.Ecommerce.payment.enums.PaymentStatus;
import com.Ecommerce.payment.exception.PaymentNotFoundException;
import com.Ecommerce.payment.repository.PaymentRepository;
import com.Ecommerce.payment.util.TransactionGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	

	private final PaymentRepository paymentRepo;

	@Override
	public PaymentResponse createPayment(PaymentRequest paymentRequest) {
		
		Payment payment=Payment.builder()
		.amount(paymentRequest.getAmount())
		.orderId(paymentRequest.getOrderId())
		.paymentStatus(PaymentStatus.PENDING)
		.paymentMethod(PaymentMethod.UPI)
    	.transactionId(TransactionGenerator.generate())
		.build();
		
		Payment savedPayment=paymentRepo.save(payment);
		
		return mapToResponse(savedPayment);
	}

	@Override
	public PaymentResponse getPaymentByOrderId(String orderId) {
		
		Payment payment=paymentRepo.findByOrderId(orderId).orElseThrow(()->new PaymentNotFoundException("pyament not found with given id: "+orderId));
		return mapToResponse(payment);
	}

	@Override
	public PaymentResponse updatPaymentStatusRequest(Long paymentId, PaymentStatusUpdateRequest paymentStatusRequest) {
		
		Payment payment=paymentRepo.findById(paymentId).orElseThrow(()->new PaymentNotFoundException("payment not found with given paymentId: "+paymentId));
		  payment.setPaymentStatus(paymentStatusRequest.getPaymentStatus());
		  return mapToResponse(payment);
		
	}
	
	
	public PaymentResponse mapToResponse(Payment payment) {
		
		return PaymentResponse.builder()
		.orderId(payment.getOrderId())
		.amount(payment.getAmount())
		.paymentId(payment.getId())
		.transactionId(payment.getTransactionId())
		.paymentStatus(payment.getPaymentStatus())
		.paymentMethod(payment.getPaymentMethod()).build();
		
	}

}
