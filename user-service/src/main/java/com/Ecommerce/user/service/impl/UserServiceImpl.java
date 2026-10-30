package com.Ecommerce.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
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
	
	System.out.println(" stored password is: "+user.getPassword()+" incoming pass is: "+loginDto.getPassword());
	
	Boolean matches=passwordEncoder.matches(loginDto.getPassword(),user.getPassword());
	
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
