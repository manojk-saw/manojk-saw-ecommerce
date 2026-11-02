package com.Ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
