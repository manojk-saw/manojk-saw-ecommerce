package com.Ecommerce.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.Ecommerce.product.dto.ProductDto;
import com.Ecommerce.product.entity.Category;
import com.Ecommerce.product.entity.Product;
import com.Ecommerce.product.mapper.ProductMapper;
import com.Ecommerce.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private  final ProductRepository productRepo;
	private final ProductMapper productMapper;

	@Override
	public ProductDto createProduct(ProductDto dto) {
		 
		Category category=null;
		if(dto.getCategoryId()!=null) {
			
			category=new Category();
			category.setId(dto.getCategoryId());
		}
		
	Product product=productMapper.toEntity(dto, category);
	Product savedProduct=productRepo.save(product);
	return productMapper.toDto(savedProduct);
		
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto dto) {
		
		Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("product not available"));
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setDiscountPrice(dto.getDiscountPrice());
		product.setBrand(dto.getBrand());
		product.setQuantity(dto.getQuantity());
		
		if(dto.getCategoryId()!=null) {
			
			Category category=new Category();
			category.setId(dto.getId());
			
		product.setCategory(category);
			
		}
		
		return productMapper.toDto(productRepo.save(product));
		
	}

	@Override
	public void deleteProductById(Long id) {
		
		productRepo.deleteById(id);
		
	}

	@Override
	public ProductDto getProductById(Long id) {
			
	 Product product =productRepo.findById(id).orElseThrow(()->new RuntimeException("product not found with the given id"));
	 
	 return productMapper.toDto(product);
	 
				
	}

	@Override
	public Page<ProductDto> getAllProducts(int page, int size, String sortBy, String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
		Pageable pageable=PageRequest.of(page, size);
		
		Page<Product> productPage=productRepo.findAll(pageable);
		
		return productPage.map(productMapper::toDto);

	}

	@Override
	public Page<ProductDto> searchProducts(String keyword, int page, int size) {
		
		Pageable pageable=PageRequest.of(page, size);
		
		Page<Product> productPage=productRepo.searchProducts(keyword, pageable);
		
        return productPage.map(productMapper::toDto);
    
	}

	@Override
	public Page<ProductDto> filterProducts(Long categoryId, double minPrice, double maxPrice, int page, int size) {
		
		Pageable pageable=PageRequest.of(page, size);
		
		Page<Product> product=productRepo.advanceFilter(null, categoryId, minPrice, maxPrice, pageable);
		return product.map(productMapper::toDto);
		
	}

}
