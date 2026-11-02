package com.Ecommerce.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ecommerce.product.dto.CategoryDto;
import com.Ecommerce.product.entity.Category;
import com.Ecommerce.product.mapper.CategoryMapper;
import com.Ecommerce.product.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository catRepo;
	private final CategoryMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		
		Category cat=mapper.toEntity(dto);
		Category savedCat=catRepo.save(cat);
		CategoryDto mappedDto=mapper.toDto(savedCat);
		return mappedDto;
		
		
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto dto) {
		
		Category savedCat=catRepo.findById(id).orElseThrow(()->new RuntimeException("category not found"));
		savedCat.setName(dto.getName());
		savedCat.setDescription(dto.getDescription());
		savedCat.setParentId(dto.getParentId());
		
		Category updated =catRepo.save(savedCat);
	    CategoryDto catDto= mapper.toDto(updated);
	    return catDto;
			
		}


	@Override
	public void deleteCategory(Long id) {
		
		catRepo.deleteById(id);
		
	}

	@Override
	public CategoryDto getCategory(Long id) {
	
		Category cat=catRepo.findById(id).orElseThrow(()->new RuntimeException("category not found"));
		return mapper.toDto(cat);

	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		 List<Category>catList= catRepo.findAll();
		List<CategoryDto> catDto= catList.stream().map(mapper::toDto).toList();
		return catDto;
			
	}

}
