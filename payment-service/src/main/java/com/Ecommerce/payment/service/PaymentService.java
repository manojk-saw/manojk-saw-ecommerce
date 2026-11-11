package com.Ecommerce.payment.service;

import com.Ecommerce.payment.dto.PaymentRequest;
import com.Ecommerce.payment.dto.PaymentResponse;
import com.Ecommerce.payment.dto.PaymentStatusUpdateRequest;

public interface PaymentService {
	
  PaymentResponse createPayment(PaymentRequest paymentRequest);
  PaymentResponse getPaymentByOrderId(String orderId);
  PaymentResponse updatPaymentStatusRequest(Long paymentId,PaymentStatusUpdateRequest paymentStatusUpdateRequest);

}
