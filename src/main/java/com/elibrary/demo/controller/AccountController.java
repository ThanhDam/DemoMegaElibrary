package com.elibrary.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;

import com.elibrary.demo.model.UserForm;
import com.elibrary.demo.model.UserLogs;
import com.elibrary.demo.model.Accounts;
import com.elibrary.demo.repository.AccountRepository;
import com.elibrary.demo.repository.UserLogRepository;
import com.elibrary.demo.service.AccountService;
import com.elibrary.demo.service.AccountServiceImpl;
import com.elibrary.demo.service.UserLogService;
import com.elibrary.demo.service.UserLogServiceImpl;
import com.elibrary.demo.service.ValidationService;
import com.elibrary.demo.service.ValidationServiceImpl;

@Controller
public class AccountController {
	
	
	private AccountRepository accountRepo;
	private AccountService accountService = new AccountServiceImpl();
	private final static Logger LOGGER = Logger.getLogger(AccountController.class.getName());
	
	@Value("${account.error.missing}")
	private String errorMissing;
	
	@Value("${account.error.login}")
	private String errorLogin;
	
	@Value("${account.error.emailExist}")
	private String errorEmailExist;
	
	@Value("${account.error.match}")
	private String errorMatch;
	
	@Value("${account.error.email}")
	private String errorEmail;
	
	@Value("${account.error.psw}")
	private String errorPsw;
	
	@GetMapping("/signup")
	protected String signup(Model model) {
		UserForm  form = new UserForm();
		model.addAttribute("userForm", form);
		return "signup";
	}
	
	@PostMapping("/signup")
	protected String saveAccount(Model model, @ModelAttribute("userForm") UserForm user) {
		ValidationService validate = new ValidationServiceImpl();
		int valEmail=validate.isEmail(user.getEmail());
		int valPassword = validate.isPassword(user.getPassword());
		if(valEmail == 1 || valPassword == 1 || user.getRePassword().isEmpty()) {
			model.addAttribute("error", errorMissing);
			return "signup";
		}else if(!user.getPassword().equals(user.getRePassword())) {
			model.addAttribute("errMatch", errorMatch);
			return "signup";
		}
		if(valEmail == 2) {
			model.addAttribute("errEmail", errorEmail);
			return "signup";
		}else if(valPassword == 2) {
			model.addAttribute("errPsw", errorPsw);
			return "signup";
		}
		Accounts accSignup = accountRepo.findByEmail(user.getEmail());
		//check email -> if exist & validFlag = 1, not allow, else, allow
		if(accSignup.getIdUser().isEmpty() || accSignup.getValidFlag()!=1) {
			accSignup.setIdUser(accountService.generateID());
			accSignup.setEmail(user.getEmail());
			accSignup.setPassword(accountService.encodePassword(user.getPassword()));
			accSignup.setIdRole(2);
			accSignup.setValidFlag(1);
			accountRepo.save(accSignup); 
			return "redirect:/login";
		}
		model.addAttribute("errEmailExist", errorEmailExist);	
		return "signup";
	}
	
	@GetMapping("/login")
	protected String startLogin(Model model) {
		UserForm usForm = new UserForm();
		model.addAttribute("userForm", usForm);
		System.out.println("LOGGER ------ " + RequestContextHolder.currentRequestAttributes().getSessionId());
		return "login";
	}
	
	@PostMapping("/login")
	protected String login(Model model, @ModelAttribute("userForm") UserForm usForm) {
		Accounts accLogin = accountRepo.findByEmail(usForm.getEmail());
		if(accountService.checkPassword(usForm.getPassword(), accLogin.getPassword()) &
				accLogin.getValidFlag()!=2) {
			saveUserLog(accLogin.getIdUser());
			return "redirect:/library";
		}
		model.addAttribute("error", errorLogin);
		return "login";
	}
	
	private void saveUserLog(String idUser) {
		UserLogService logService = new UserLogServiceImpl();
		UserLogs logs = new UserLogs();
		logs.setIdLog(logService.generateIdLog());
		logs.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		logs.setStartTime(new Date());
		logs.setIdUser(idUser);
		UserLogRepository logRepository = null;
		logRepository.save(logs);
		System.out.println("Save userLogs succesfull!");
	}
	
	/*
	 * Admin
	 */
	@GetMapping("/admin/accountmanager")
	protected String getAllAccounts(Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			List<Accounts> lstAccounts = accountRepo.findAll();
			model.addAttribute("lstAccounts", lstAccounts);
			return "admin/accountsmana";
		}
		return "redirect:/404";
	}
	
	@GetMapping("/admin/accountmanager/{id}")
	protected String getAccountById(@PathVariable String id, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			Accounts acc = accountRepo.findById(id).orElseThrow(NullPointerException::new);
			model.addAttribute("account", acc);
			return "admin/accountsmana";
		}
		return "redirect:/404";
	}
}
