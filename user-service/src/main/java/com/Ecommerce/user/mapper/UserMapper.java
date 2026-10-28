package com.Ecommerce.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.entity.User;

@Component
public class UserMapper {
	
	private PasswordEncoder passwordEncoder;
	
	public UserMapper(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder=passwordEncoder;
	}
	
	public User toEntity(UserCreateRequestDto dto) {
		
		return null;
	}

}
