package com.Ecommerce.user.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String  authHeader=request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			
			final String extractedtoken=authHeader.substring(7);
			
			try {
			jwtUtil.validateToken(extractedtoken);	
			
		 Long userId=jwtUtil.getUserIdFromToken(extractedtoken);
		 
		 UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userId,null,Collections.emptyList());
		SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}catch(Exception e){
			
			SecurityContextHolder.clearContext();
			}
			
		}
		
		filterChain.doFilter(request, response);	
		
	}
	
	

}
