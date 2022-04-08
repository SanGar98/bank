package com.vortexbird.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexbird.bank.doamin.DocumentType;
import com.vortexbird.bank.dto.DocumentTypeDTO;

@Mapper
public interface DocumenTyprMapper {
	public DocumentTypeDTO toDocTypeDTO(DocumentType documentType);
	
	public DocumentType toDocType(DocumentTypeDTO documentTypeDTO);
	
	public List<DocumentTypeDTO> toDocTypeDTOs(List<DocumentType> customers);
	
	public List<DocumentType> toDocTypes (List<DocumentTypeDTO> customerDTOs);
		
	
}
