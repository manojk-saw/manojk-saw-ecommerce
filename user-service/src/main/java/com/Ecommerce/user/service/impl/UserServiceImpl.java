package com.Ecommerce.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ecommerce.user.dto.LoginRequestDto;
import com.Ecommerce.user.dto.LoginResponseDto;
import com.Ecommerce.user.dto.UserCreateRequestDto;
import com.Ecommerce.user.dto.UserResponseDto;
import com.Ecommerce.user.entity.User;
import com.Ecommerce.user.exception.ResourceAlreadyExistException;
import com.Ecommerce.user.exception.ResourceNotFoundException;
import com.Ecommerce.user.mapper.AuthMapper;
import com.Ecommerce.user.mapper.UserMapper;
import com.Ecommerce.user.repository.UserRepository;
import com.Ecommerce.user.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepo;
	private UserMapper userMapper;
	private PasswordEncoder passwordEncoder;
	private JwtUtil jwtUtil;
	private AuthMapper authMapper;

	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		
		if(userRepo.existsByEmail(dto.getEmail())) {
			
			throw new ResourceAlreadyExistException("Email Already present");
		}
		
		User user=userMapper.toEntity(dto);
		User savedUser=userRepo.save(user);
		return userMapper.toDto(savedUser);
		
		
	}

	@Override
	public LoginResponseDto login(LoginRequestDto loginDto) {
		
	User user=userRepo.findByEmail(loginDto.getEmail()).orElseThrow(()->new ResourceNotFoundException("Email not found"));
	
	Boolean matches=passwordEncoder.matches(loginDto.getPassword(),user.getEmail());
	
	if(!matches) {
		throw new ResourceNotFoundException("wrong password");
	
		}
	
	String token =jwtUtil.generateToken(user.getId(),user.getEmail() ,user.getRoles());
	return authMapper.toLoginResponse(user, token);
	
	}

	@Override
	public UserResponseDto getById(Long id) {
		
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with id: "+id));
		return userMapper.toDto(user);
	}

}
