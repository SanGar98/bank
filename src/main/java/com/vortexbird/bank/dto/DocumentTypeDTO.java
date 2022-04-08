package com.vortexbird.bank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDTO {
	@NotNull
	@Min(0)
	private Integer dotyId;

	@NotNull
	private Boolean enable;

	@NotNull
	@Size(min=4, max=255)
	@NotEmpty
	private String name;
}
