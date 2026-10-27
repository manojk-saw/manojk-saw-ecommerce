package com.Ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
	
	private String token;
	private String TokenType="Bearer";
	private Long userId;
	private String name;
	private String email;
	

}
