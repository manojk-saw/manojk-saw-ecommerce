package com.Ecommerce.product.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Ecommerce.product.dto.ProductDto;
import com.Ecommerce.product.entity.Category;
import com.Ecommerce.product.entity.Product;

@Component
public class ProductMapper {
	
	@Autowired
	private Category category;
	
	public Product toEntity(ProductDto dto) {
		
		return Product.builder()
		.name(dto.getName())
		.description(dto.getDescription())
		.price(dto.getPrice())
		.discountPrice(dto.getDiscountPrice())
		.quantity(dto.getQuantity())
		.brand(dto.getBrand())
		.imageUrl(dto.getImageUrl())
		.category(category)
		.build();
		
	}
	
	
	public ProductDto toDto(Product product) {
		
		return ProductDto.builder()
		.id(product.getId())
		.name(product.getName())
		.description(product.getDescription())
		.brand(product.getBrand())
		.price(product.getPrice())
		.discountPrice(product.getDiscountPrice())
		.imageUrl(product.getImageUrl())
		.quantity(product.getQuantity())
		.categoryId(
				     product.getCategory()!=null? product.getCategory().getId() :null)
		.build();	
	}
	
}
