package com.Ecommerce.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.Ecommerce.notification.dto.NotificationEvent;
import com.Ecommerce.notification.service.EmailService;
import com.Ecommerce.notification.service.SmsService;
import com.Ecommerce.notification.service.SmsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class notificationConsumer {
	
	private final EmailService emailService;
	private final SmsService smsService;
	
	@KafkaListener(topics="Notification-topic",groupId="notification-group")
	public void consumeMessage(NotificationEvent event) {
		
		if("EMAIL".equals(event.getType())) {
				
			emailService.sendMail(event.getEmail(),event.getSubject(),event.getMessage());
		
		}
		
		if("SMS".equals(event.getType())) {
			
			smsService.sendSms(event.getMobile(), event.getMessage());
		}
	}
}
