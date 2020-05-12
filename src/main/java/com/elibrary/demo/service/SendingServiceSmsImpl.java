package com.elibrary.demo.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SendingServiceSmsImpl implements SendingService{
	public static final String ACCOUNT_SID = "ACff62ac8062bb539f9594d6634c8564ab";
	public static final String AUTH_TOKEN = "6618b427482070d84b198abcb7662955";
	
	@Override
	public void sending(String address, String message) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message mess = Message
				.creator(new PhoneNumber(address), new PhoneNumber("+12057518033"), message)
				.create();

		System.out.println("Sending By SMS Done!" + mess.getSid());
	}

}
