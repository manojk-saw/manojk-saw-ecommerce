package com.Ecommerce.product.service;

import org.springframework.data.domain.Page;

import com.Ecommerce.product.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto dto);
	ProductDto updateProduct(Long id,ProductDto dto);
	void deleteProductById(Long id);
	ProductDto getProductById(Long id);
	Page<ProductDto> getAllProducts(int page, int size,String sortBy,String sortDir );
	Page<ProductDto> searchProducts(String keyword,int page,int size);
	Page<ProductDto> filterProducts(Long categoryId,double minPrice,double maxPrice, int page,int size);
	
	
}
