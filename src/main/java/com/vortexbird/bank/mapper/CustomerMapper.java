package com.vortexbird.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vortexbird.bank.doamin.Customer;
import com.vortexbird.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
	@Mapping(source = "documentType.dotyId", target = "dotyId")
	public CustomerDTO toCustomerDTO(Customer customer);
	
	@Mapping(target = "documentType.dotyId", source = "dotyId")
	public Customer toCustomer(CustomerDTO customerDTO);
	
	public List<CustomerDTO> toCustomerDTOs(List<Customer> customers);
	
	public List<Customer> toCustomers (List<CustomerDTO> customerDTOs);
}
