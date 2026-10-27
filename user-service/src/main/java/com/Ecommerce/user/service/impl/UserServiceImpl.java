package com.Ecommerce.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ecommerce.user.dto.LoginRequestDto;
import com.Ecommerce.user.dto.LoginResponseDto;
import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.dto.UserResponseDto;
import com.Ecommerce.user.entity.User;
import com.Ecommerce.user.repository.UserRepository;


public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		
		if(userRepo.existsByEmail(dto.getEmail())) {	
			throw new RuntimeException("Email already exist");	
		}
		
		User user=new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		
		User savedUser =userRepo.save(user);
		return new UserResponseDto();
		
		
	}

	@Override
	public LoginResponseDto login(LoginRequestDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
