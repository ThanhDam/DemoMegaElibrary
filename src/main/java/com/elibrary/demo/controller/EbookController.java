package com.elibrary.demo.controller;

import org.apache.http.annotation.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EbookController {

	@GetMapping("/library")
	protected String library() {
		return "library";
	}
}
