package com.elibrary.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.apache.http.annotation.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elibrary.demo.model.Ebooks;
import com.elibrary.demo.repository.EbookRepository;
import com.elibrary.demo.service.EbookService;
import com.elibrary.demo.service.EbookServiceImpl;

@Controller
public class EbookController {
	
	private EbookRepository ebookRepo;
	
	private EbookService ebookService = new EbookServiceImpl();
	
	private final static Logger LOGGER = Logger.getLogger(EbookController.class.getName());

	@GetMapping("/library")
	protected String library(Model model) {
		List<Ebooks> lstEbook = ebookRepo.findAll();
		model.addAttribute("lstEbooks", lstEbook);
		return "library";
	}
	
	
}
