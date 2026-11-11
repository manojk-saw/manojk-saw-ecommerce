package com.Ecommerce.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.Ecommerce.payment.enums.PaymentMethod;
import com.Ecommerce.payment.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name="payments", indexes= { @Index(columnList="orderId"),
                                   @Index(columnList="transactionId")})
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
	@Column(nullable = false)
		private String orderId;
	
	@Column(nullable = false, precision = 12, scale = 2)
		private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
		private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
		private PaymentMethod paymentMethod;
	
	@Column(unique = true, nullable = false)
		private String transactionId;
		private String gatewayPaymentId;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
	
	@PrePersist
	public void onCreate() {
		
		createdAt=LocalDateTime.now();
		updatedAt=LocalDateTime.now();
	}
	
	@PreUpdate
	public void onUpdate() {
		updatedAt=LocalDateTime.now();
	}
}
