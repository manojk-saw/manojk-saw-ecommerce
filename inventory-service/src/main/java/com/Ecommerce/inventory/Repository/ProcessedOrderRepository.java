package com.Ecommerce.inventory.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.inventory.entity.ProcessedOrder;

public interface ProcessedOrderRepository extends JpaRepository<ProcessedOrder,Long>{
	
	Optional<ProcessedOrder> existsByOrderId(String orderId);

}
