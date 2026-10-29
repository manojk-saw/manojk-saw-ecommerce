package com.Ecommerce.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.user.dto.LoginRequestDto;
import com.Ecommerce.user.dto.LoginResponseDto;
import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.dto.UserResponseDto;
import com.Ecommerce.user.service.impl.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto dto) {
		
		UserResponseDto created=userService.register(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto>login(@Valid  @RequestBody LoginRequestDto dto){
		
		LoginResponseDto loggedInUser=	userService.login(dto);
		return ResponseEntity.ok(loggedInUser);
			
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id, Authentication authentication){
		
		UserResponseDto dto=userService.getById(id);
		
		return ResponseEntity.ok(dto);
		
	}
	
	
}
