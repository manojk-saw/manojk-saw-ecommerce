package com.Ecommerce.user.mapper;

import org.springframework.stereotype.Component;

import com.Ecommerce.user.dto.LoginResponseDto;
import com.Ecommerce.user.entity.User;

@Component
public class AuthMapper {
	
	public LoginResponseDto toLoginResponse(User user,String token) {
		return new LoginResponseDto(token,"Bearer",user.getId(),user.getName(),user.getEmail());
	}

}
