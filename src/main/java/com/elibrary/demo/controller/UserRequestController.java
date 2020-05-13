package com.elibrary.demo.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;

import com.elibrary.demo.model.RequestForm;
import com.elibrary.demo.model.UserLogs;
import com.elibrary.demo.model.UserRequests;
import com.elibrary.demo.repository.AccountRepository;
import com.elibrary.demo.repository.UserLogRepository;
import com.elibrary.demo.repository.UserRequestRepository;
import com.elibrary.demo.service.AccountService;
import com.elibrary.demo.service.AccountServiceImpl;
import com.elibrary.demo.service.UserRequestService;
import com.elibrary.demo.service.UserRequestServiceImpl;
import com.elibrary.demo.service.ValidationService;
import com.elibrary.demo.service.ValidationServiceImpl;

@Controller
public class UserRequestController {
	private UserRequestRepository requestRepo;
	
	private UserRequestService requestService = new UserRequestServiceImpl();
	
	private AccountService accountService = new AccountServiceImpl();
	
	private final static Logger LOGGER = Logger.getLogger(UserRequestController.class.getName());
	private final static String MESS_REPLY = "Please click this link to sign up: 127.0.0.1:8080/signup ";

	@Value("${registration.error.mising}")
	private String missingErr;
	@Value("${registration.error.email}")
	private String emailErr;
	@Value("${registration.error.phone}")
	private String phoneErr;
	
	@GetMapping("/registration")
	protected String register(Model model) {
		RequestForm form = new RequestForm();
		model.addAttribute("requestForm", form);
		return "registration";
	}
	
	@PostMapping("/registration")
	protected String saveRequest(Model model, @ModelAttribute("requestForm") RequestForm rqForm) {
		String id = requestService.generateIdRequest();
		String email = rqForm.getEmail();
		String phone = rqForm.getPhone();
		
		ValidationService validate = new ValidationServiceImpl();
		int valEmail = validate.isEmail(rqForm.getEmail());
		int valPhone = validate.isPhone(rqForm.getPhone());
		if(valEmail == 1 && valPhone == 1) {
			model.addAttribute("errMissing", missingErr);
		}else if(valEmail == 2) {
			model.addAttribute("errEmail", emailErr);
		}else if(valPhone == 2) {
			model.addAttribute("errPhone", phoneErr);
		}else {
			UserRequests request = new UserRequests(id, email, phone, 1);
			requestRepo.save(request);
			return "redirect:/successRegister";
		}
		return "registration";
		
	}
	
	/*
	 * Admin
	 */
	@GetMapping("/admin/requestmanager")
	protected String responce(Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			List<UserRequests> lstRequests = requestRepo.getAllActiveRequest();
			model.addAttribute("lstRequests", lstRequests);
			return "admmin/requestmana";
		}
		return "redirect:/401";
	}
	
	@GetMapping("/admin/requestmanager/{id}")
	protected String loadRequest(@PathVariable String id, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			UserRequests request = requestRepo.findById(id).orElseThrow(NullPointerException::new);
			model.addAttribute("request", request);
			return "admin/requestsmana";
		}
		return "redirect:/401";
	}
	//button reply
	@PatchMapping("/admin/requestmanager/{id}")
	protected String replyRequest(@ModelAttribute("request") UserRequests req, @PathVariable("id") String id, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			UserRequests request = requestRepo.findById(id).orElseThrow(NullPointerException::new);
			SendingController sending;
			if(!request.getEmail().isEmpty()) {
				sending = new SendingControllerEmailImpl();
				sending.processSending(request.getEmail(), MESS_REPLY);
			}else {
				sending = new SendingControllerSmsImpl();
				sending.processSending(request.getPhone(), MESS_REPLY);
			}
			LOGGER.getLogger("---------------Reply request success!");
			request.setValidFlag(2);
			requestRepo.save(request);
			return "admin/requestsmana";
		}
		return "redirect:/401";
	}
}
