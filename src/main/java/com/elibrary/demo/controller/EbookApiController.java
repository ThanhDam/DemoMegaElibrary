package com.elibrary.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elibrary.demo.model.Ebooks;
import com.elibrary.demo.repository.EbookRepository;

@RestController
@RequestMapping("/api/demo")
public class EbookApiController {
	
	private EbookRepository ebookRepo;
	
	@GetMapping("/ebooks")
	protected List<Ebooks> getAll(){
		return ebookRepo.findAll();
	}
	
	@GetMapping("/ebooks/{id}")
	protected ResponseEntity<Ebooks> getEbookById(@PathVariable(name = "id") String id){
		Ebooks ebook = ebookRepo.findById(id).orElseThrow(NullPointerException::new);
		return ResponseEntity.ok().body(ebook);
	}
	
	@PostMapping("/ebooks")
	protected Ebooks addEbook(@Valid @RequestBody Ebooks eb) {
		return ebookRepo.save(eb);
	}
	
	@PutMapping("/ebooks/{id}")
	protected ResponseEntity<Ebooks> updateEbook(@PathVariable(name = "id") String id,
			@Valid @RequestBody Ebooks eb){
		Ebooks e = ebookRepo.findById(id).orElseThrow(NullPointerException::new);
		e.setAuthor(eb.getAuthor());
		e.setDirectory(eb.getDirectory());
		e.setIdCategory(eb.getIdCategory());
		e.setImage(eb.getImage());
		e.setTitle(eb.getTitle());
		Ebooks ebook = ebookRepo.save(e);
		return ResponseEntity.ok(ebook);
	}
}
