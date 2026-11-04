package com.Ecommerce.product.mapper;

import org.springframework.stereotype.Component;

import com.Ecommerce.product.dto.CategoryDto;
import com.Ecommerce.product.entity.Category;

@Component
public class CategoryMapper {
	
	
	public Category toEntity(CategoryDto dto) {
		
		return Category.builder()
		.name(dto.getName())
		.description(dto.getDescription())
		.parentId(dto.getParentId())
		.build();
		
	}
	
	public CategoryDto toDto(Category category) {
		
		return CategoryDto.builder()
		.id(category.getId())
		.name(category.getName())
		.description(category.getDescription())
		.parentId(category.getParentId())
		.build();	
		
	}

}
