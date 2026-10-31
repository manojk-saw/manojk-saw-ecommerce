package com.Ecommerce.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	Page<Product> findByCategory(Long categoryId,Pageable pageable);
	Page<Product> findByPriceBetween(Double min, Double max,Pageable pageable);
	Page<Product> findByNameContaingIgnoreCase(String keyword,Pageable pageable);
	
	

}
