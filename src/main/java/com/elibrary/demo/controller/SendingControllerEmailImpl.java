package com.elibrary.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.elibrary.demo.service.SendingService;

@Service
public class SendingControllerEmailImpl implements SendingController{
	@Autowired
	@Qualifier("sendingServiceEmailImpl")
	private SendingService service;
	
	public SendingService getService() {
		return service;
	}

	public void setService(SendingService service) {
		this.service = service;
	}
	
	public SendingControllerEmailImpl() {
		
	}

	@Override
	public void processSending(String address, String message) {
		service.sending(address, message);
	}

}
