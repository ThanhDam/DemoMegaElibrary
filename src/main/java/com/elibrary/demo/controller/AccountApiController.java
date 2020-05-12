package com.elibrary.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elibrary.demo.model.Accounts;
import com.elibrary.demo.repository.AccountRepository;
import com.elibrary.demo.service.AccountService;
import com.elibrary.demo.service.AccountServiceImpl;

@RestController
@RequestMapping(value = "/api/demo")
public class AccountApiController {
	
	private AccountRepository accountRepo;
	
	
	private AccountService accService = new AccountServiceImpl();
	
	
	@Autowired
	public AccountApiController(AccountRepository accountRepo, AccountService accService) {
		this.accountRepo = accountRepo;
		this.accService = accService;
	}

	@GetMapping(value = "/accounts")
	protected List<Accounts> getAll(){
		return accountRepo.findAll();
	}
	
	@GetMapping(value = "/accounts/{id}")
	protected ResponseEntity<Accounts> getAccountById(@PathVariable(name = "id")String id) throws NullPointerException{
		Accounts account= accountRepo.findById(id).orElseThrow(NullPointerException::new);
		return ResponseEntity.ok().body(account);
//		return ResponseEntity.ok(account);
	}
	
	@PostMapping(value = "/accounts")
	protected Accounts addAccount(@Valid @RequestBody Accounts account){
		account.setPassword(accService.encodePassword(account.getPassword()));
		return accountRepo.save(account);
	}
	
	@PutMapping(value = "/accounts/{id}")
	protected ResponseEntity<Accounts> updateAccountById(@PathVariable(name = "id") String id,
			@Valid @RequestBody Accounts acc){
		Accounts account = accountRepo.findById(id).orElseThrow(NullPointerException::new);
		
		/**
		 * Encode before save in DB
		 */
		account.setPassword(accService.encodePassword(acc.getPassword()));
		account.setEmail(acc.getEmail());
		account.setIdRole(acc.getIdRole());
		account.setValidFlag(acc.getValidFlag());
		Accounts updateAcc = accountRepo.save(account);
		return ResponseEntity.ok(updateAcc);
	}
}
