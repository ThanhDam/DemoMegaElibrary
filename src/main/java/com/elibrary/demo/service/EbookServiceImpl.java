package com.elibrary.demo.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.demo.repository.EbookRepository;

@Service
public class EbookServiceImpl implements EbookService{

	private final static Logger LOGGER = Logger.getLogger(EbookServiceImpl.class.getName());
	private final static int INIT_LENGTH = 9; 
	
	@Autowired
	private EbookRepository ebookRepo;
	
	@Override
	public String generateIdEbook() {
		int size = ebookRepo.findAll().size() + 1;
		String sizeLength = String.valueOf(size);
		String id="";
		if(sizeLength.length()<=INIT_LENGTH) {
			int number= INIT_LENGTH-sizeLength.length();
			for(int i=0; i<number; i++) {
				id+="0";
			}
			id+=size;
		}else {
			LOGGER.setLevel(Level.INFO);
			LOGGER.getLogger("Out of range in Ebooks");
		}
		return id;
	}
}
