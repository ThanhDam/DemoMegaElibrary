package com.elibrary.demo.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SendingServiceEmailImpl implements SendingService {
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "thanhdam2411@gmail.com";
	private static final String PASSWORD = "rnsloxsyakwejpcf";//mail - windows computer
	
	@Override
	public void sending(String address, String message) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS
		
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		Message mess = new MimeMessage(session);
		
		
		try {
			//from
			mess.setFrom(new InternetAddress("thanhdam2411@gmail.com"));
			//to
			mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address, false));
			mess.setSubject("[REPLY] REQUEST REGISTRATION ELIBRARY");
			mess.setText(message);
			
			
			Transport.send(mess);
			
			System.out.println("======= Sending By Email Done!");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
