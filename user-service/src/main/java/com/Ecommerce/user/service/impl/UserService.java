package com.Ecommerce.user.service.impl;

import com.Ecommerce.user.dto.LoginRequestDto;
import com.Ecommerce.user.dto.LoginResponseDto;
import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.dto.UserResponseDto;

public interface UserService {
	
	UserResponseDto  register(UserCreateRequestDto dto);
	LoginResponseDto login(LoginRequestDto loginDto);
	
	UserResponseDto getById(Long id);
}
