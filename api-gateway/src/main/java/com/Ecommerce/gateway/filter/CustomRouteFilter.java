package com.Ecommerce.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.Ecommerce.gateway.security.JwtGatewayFilter;

import reactor.core.publisher.Mono;

@Component
public class CustomRouteFilter extends AbstractGatewayFilterFactory<Object> {
	
	 private final JwtGatewayFilter jwtGatewayFilter;

	    public CustomRouteFilter(JwtGatewayFilter jwtGatewayFilter) {
	        super(Object.class);
	        this.jwtGatewayFilter = jwtGatewayFilter;
	    }

	@Override
	public GatewayFilter apply(Object config) {
		
		return (exchange, chain) -> {

            System.out.println("Route Filter: Before routing");

            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("X-Route-Header", "Added by Gateway")
                    .build();
            
      ServerWebExchange modifiedExchange=  exchange
    		  .mutate().request(modifiedRequest).build();

      return jwtGatewayFilter.filter(modifiedExchange, chain)
              .then(Mono.fromRunnable(() -> {
                  System.out.println("Route Filter: After routing");
              }));
        };
	}

}
