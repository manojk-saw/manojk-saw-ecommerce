package com.Ecommerce.payment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{
	
	Optional<Payment> findByOrderId(String orderId);
	Optional<Payment> findByTransactionId(String transactionId);

}
