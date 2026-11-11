package com.Ecommerce.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TwilioConfiguration {
	
	@Value("${twilio.account-sid}")
	private String accountSid;
	
	@Value("${twilio.auth-token}")
	private String authToken;
	
//	@Value("${twilio.from-number}")
//	private String number;
	
	@PostConstruct
	void init() {
		
		log.info("going to inject value from properties file: "+accountSid);
		log.info("going to inject value from properties file: "+authToken);
		
		Twilio.init(authToken,accountSid);
		
		log.info("injected value from properties file: "+accountSid);
		log.info("injected value from properties file: "+authToken);
		
		
	}

}
