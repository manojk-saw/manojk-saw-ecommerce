package com.Ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
	
	@NotBlank(message="Name Required")
	private String name;
	
	@Email(message="Valid email required")
	@NotBlank(message="Email Required")
	private String email;
	@NotBlank(message="Password Required")
	@Size(min=6,message="Password must be atleast 6 characters")
	private String password;
	private String phone;

}
