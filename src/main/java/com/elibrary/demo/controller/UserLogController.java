package com.elibrary.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;

import com.elibrary.demo.model.UserLogs;
import com.elibrary.demo.repository.UserLogRepository;
import com.elibrary.demo.service.AccountService;
import com.elibrary.demo.service.AccountServiceImpl;
import com.elibrary.demo.service.UserLogService;
import com.elibrary.demo.service.UserLogServiceImpl;

@Controller
public class UserLogController {
	private UserLogRepository userLogRepo;
	
	private UserLogService userLogService = new UserLogServiceImpl();
	private AccountService accountService = new AccountServiceImpl();
	
	private final static Logger LOGGER = Logger.getLogger(UserLogController.class.getName());
	
	@GetMapping("/admin/logsmanager")
	protected String getAll(Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			List<UserLogs> lstLog = userLogRepo.findAll();
			model.addAttribute("lstUserLogs", lstLog);
			return "admin/logsmana";
		}
		return "redirect:/401";
	}
	
	@GetMapping("/admin/logsmanager/{id}")
	protected String getByIdUser(@PathVariable("id") String id, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			List<UserLogs> lstLog = userLogRepo.findByUser(id);
			model.addAttribute("lstUserLogs", lstLog);
			return "admin/logsmana";
		}
		return "redirect:/401";
	}
	
}
