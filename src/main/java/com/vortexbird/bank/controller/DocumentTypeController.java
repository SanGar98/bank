package com.vortexbird.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.bank.doamin.Customer;
import com.vortexbird.bank.doamin.DocumentType;
import com.vortexbird.bank.dto.CustomerDTO;
import com.vortexbird.bank.dto.DocumentTypeDTO;
import com.vortexbird.bank.mapper.DocumenTyprMapper;
import com.vortexbird.bank.service.DocumentTypeService;

@RestController
@RequestMapping("/api/v1/document-types")
@CrossOrigin("*")
public class DocumentTypeController {
	@Autowired
	DocumentTypeService documentTypeService;
	
	@Autowired
	DocumenTyprMapper documentTypeMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
		DocumentType documentType = null;
		DocumentTypeDTO documentTypeDTO = null;
		
		Optional<DocumentType> docuTypeOptional=documentTypeService.findById(id);
		
		if(docuTypeOptional.isPresent()==false) {
			return ResponseEntity.ok().body(null);
		}
		documentType = docuTypeOptional.get();
		documentTypeDTO = documentTypeMapper.toDocTypeDTO(documentType);
		return ResponseEntity.ok().body(documentTypeDTO);
	}
	@GetMapping
	public ResponseEntity<?> findAll()throws Exception{
		List<DocumentType> documentType = documentTypeService.findAll();
		List<DocumentTypeDTO> documentTypeDTOs = documentTypeMapper.toDocTypeDTOs(documentType);
		return ResponseEntity.ok().body(documentTypeDTOs);	
	}
}
