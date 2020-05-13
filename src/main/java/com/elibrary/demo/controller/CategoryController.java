package com.elibrary.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;

import com.elibrary.demo.model.Categories;
import com.elibrary.demo.model.CategoryForm;
import com.elibrary.demo.repository.CategoryRepository;
import com.elibrary.demo.service.AccountService;
import com.elibrary.demo.service.AccountServiceImpl;

@Controller
public class CategoryController {
	private CategoryRepository categoryRepo;
	private AccountService accountService = new AccountServiceImpl();
	private final static Logger LOGGER = Logger.getLogger(CategoryController.class.getName());
	
	@GetMapping("/admin/categorymanager")
	protected String getAllCategory(Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			List<Categories> lstCategories = categoryRepo.findAll();
			model.addAttribute("lstCategory", lstCategories);
			return "admin/categoriesmana";
		}
		return "redirect:/401";
	}
	
	@GetMapping("/admin/categories")
	protected String createCategory(Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			CategoryForm cateForm = new CategoryForm();
			model.addAttribute("categoryForm", cateForm);
			return "admin/categories";
		}
		return "redirect:/401";
	}
	
	@PostMapping("/admin/categories")
	protected String saveCategory(@ModelAttribute("categoryForm") Categories cate, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			Categories category = new Categories();
			category.setShortCode(cate.getShortCode());
			category.setDescription(cate.getDescription());
			categoryRepo.save(category);
			return "admin/categories";
		}
		return "redirect:/401";
	}
	
	@GetMapping("/admin/categories/{id}")
	protected String getCateById(@PathVariable("id")int id, Model model) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			Categories cate = categoryRepo.getOne(id);
			model.addAttribute("cateForm", cate);
			return "admin/categories";
		}
		return "redirect:/401";
	}
	
	@PatchMapping("/admin/categories/{id}")
	protected String editCategory(@ModelAttribute("cateForm")Categories cate, @PathVariable("id") int id, Model model ) {
		boolean isAdmin = accountService.checkAdminBySession(RequestContextHolder.currentRequestAttributes().getSessionId());
		if(isAdmin) {
			Categories temp = new Categories();
			temp.setIdCategory(id);
			Categories cateUpdate = categoryRepo.findOne(Example.of(temp)).orElseThrow(NullPointerException::new);
			cateUpdate.setDescription(cate.getDescription());
			cateUpdate.setShortCode(cate.getShortCode());
			categoryRepo.save(cateUpdate);
			LOGGER.getLogger("-------------Updated Category success!");
			return "admin/categories";
		}
		return "redirect:/401";
	}

}
