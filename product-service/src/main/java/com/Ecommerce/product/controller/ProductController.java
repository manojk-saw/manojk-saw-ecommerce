package com.Ecommerce.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Ecommerce.product.dto.ProductDto;
import com.Ecommerce.product.service.ProductService;

@RestController
@RequestMapping("/api/products")

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto){
		
		ProductDto productDto=productService.createProduct(dto);
		return ResponseEntity.ok(productDto);
			
	}
	
	@PutMapping("/udpate/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@RequestBody ProductDto dto){
		
		return ResponseEntity.ok().body(productService.updateProduct(id, dto));	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		return ResponseEntity.ok("products deleted successfully");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto>getProduct(@PathVariable Long id){
		
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDto>> getALLProducts(@RequestParam(defaultValue="0")int page,
			 @RequestParam(defaultValue="10") int size,
			 @RequestParam(defaultValue="id")String sortyBy,
			 @RequestParam(defaultValue="asc")String sortDir){
		
	 return ResponseEntity.ok(productService.getAllProducts(page, size, sortyBy, sortDir));
	}
	
	@GetMapping("/serach")
	public ResponseEntity<Page<ProductDto>> searchProduct(@RequestParam String keyword,
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="10")int size){
		
		return ResponseEntity.ok(productService.searchProducts(keyword, page, size));
	}
	
	public ResponseEntity<Page<ProductDto>> filterProducts(@RequestParam(required=false) Long categoryId,
			@RequestParam(required=false)double minPrice,
			@RequestParam(required=false) double maxPrice,
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="10")int size){
		
		return ResponseEntity.ok(productService.filterProducts(categoryId, minPrice, maxPrice, page, size));
	}
	
	
	

}
