package com.Ecommerce.gateway.security;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import jakarta.ws.rs.core.HttpHeaders;
import reactor.core.publisher.Mono;

@Component
public class JwtGatewayFilter implements GatewayFilter,Ordered {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final List<String>PUBLIC_URLS=List.of("/api/users/register","/api/users/login");

	@Override
	public int getOrder() {
		
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		String path=exchange.getRequest().getURI().getPath();
		String method=exchange.getRequest().getMethod().name();
		
		System.out.println("========== API GATEWAY ==========");
		System.out.println("Incoming Path: " + path);
		System.out.println("HTTP Method: " + method);
		
		if(PUBLIC_URLS.stream().anyMatch(path::startsWith)) {
			
			return chain.filter(exchange);
		}
		
		String authHeader=exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")){
			
			return unauthorize(exchange);
		}
		
		String token=authHeader.substring(7);
		
		boolean isValidated=jwtUtil.vlidateToken(token);
		
		if(!isValidated) {
			
			return unauthorize(exchange);
		}
		
		String userId=jwtUtil.getUserId(token);
		String role=jwtUtil.getRole(token);
		
		if(role!=null  && role.startsWith("ROLE_")) {
			role=role.substring(5);	
		}
		
		if(!isAuthorized(role,path,method)) {
		
			return forbidden(exchange);
		}
	
		ServerHttpRequest  modifiedRequest=exchange.getRequest().mutate().header("X-User-Id",userId).header("X-Role",role).build();
  
		ServerWebExchange request=exchange.mutate().request(modifiedRequest).build();
		return chain.filter(request);
		
	}

	private Mono<Void> unauthorize(ServerWebExchange exchange) {
		
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		return exchange.getResponse().setComplete();
		
	}
	
	private static final Map<String,Map<String,List<String>>>ROLE_API_PERMISSION=Map.of(
			
			"ADMIN",
				Map.of(
						"GET",List.of("/api/orders/","/api/categories","/api/products","/api/inventory","/api/payments"),
						"POST", List.of("/api/products", "/api/categories", "/api/inventory"),
						"PUT", List.of("/api/products", "/api/categories", "/api/payments"),
				        "DELETE", List.of("/api/products", "/api/categories")),
				        
					
			"USER", Map.of(
					        "GET", List.of("/api/products", "/api/categories", "/api/inventory", "/api/payments"),
					        "POST", List.of("/api/categories", "/api/products", "/api/order", "/api/payment"))
			);
					
	
	
	private boolean isAuthorized(String role, String path, String method) {
				Map<String, List<String>> permission  = ROLE_API_PERMISSION.get(role);
				if(permission == null) return false;
				List<String> allowedPaths = permission.get(method);
				if(allowedPaths==null) return false;
				return allowedPaths.stream().anyMatch(path:: startsWith);
			}
	private Mono<Void> forbidden(ServerWebExchange exchange){
				exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
				return exchange.getResponse().setComplete();
			}	
}
