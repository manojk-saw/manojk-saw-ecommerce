package com.Ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.product.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	

}
