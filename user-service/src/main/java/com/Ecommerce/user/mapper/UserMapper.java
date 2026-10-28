package com.Ecommerce.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.dto.UserResponseDto;
import com.Ecommerce.user.entity.User;

@Component
public class UserMapper {
	
	private PasswordEncoder passwordEncoder;
	
	public UserMapper(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder=passwordEncoder;
	}
	
	public User toEntity(UserCreateRequestDto dto) {
		
		return User.builder()
		.name(dto.getName())
		.email(dto.getEmail())
		.phone(dto.getPhone())
		.password(passwordEncoder.encode(dto.getPassword()))
		.roles("USER_ROLE").build();
	
	}
	
	public UserResponseDto toDto(User user) {
		
		return UserResponseDto.builder()
		.id(user.getId())
		.name(user.getName())
		.email(user.getEmail())
		.phone(user.getPhone())
		.roles(user.getRoles())
		.createdAt(user.getCreatedAt())
		.updatedAt(user.getUpdatedAt()).build();
	}

}
