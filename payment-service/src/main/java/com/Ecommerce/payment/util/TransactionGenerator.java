package com.Ecommerce.payment.util;

import java.util.UUID;

public class TransactionGenerator {
	
	public static String generate() {
		
		return "trx"+UUID.randomUUID();
	}

}
