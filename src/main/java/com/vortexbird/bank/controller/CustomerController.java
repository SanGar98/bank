package com.vortexbird.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.bank.doamin.Customer;
import com.vortexbird.bank.dto.CustomerDTO;
import com.vortexbird.bank.mapper.CustomerMapper;
import com.vortexbird.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CustomerDTO customerDTO)throws Exception{
		Customer customer = customerMapper.toCustomer(customerDTO);
		customer=customerService.save(customer);
		customerDTO = customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid CustomerDTO customerDTO)throws Exception{
		Customer customer = customerMapper.toCustomer(customerDTO);
		customer=customerService.update(customer);
		customerDTO = customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception{
		customerService.deleteById(id);
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll()throws Exception{
		List<Customer> customers = customerService.findAll();
		List<CustomerDTO> customerDTOs = customerMapper.toCustomerDTOs(customers);
		
		return ResponseEntity.ok().body(customerDTOs);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
		Customer customer = null;
		CustomerDTO customerDTO = null;
		
		Optional<Customer> customerOptional=customerService.findById(id);
		
		if(customerOptional.isPresent()==false) {
			return ResponseEntity.ok().body(null);
		}
		customer = customerOptional.get();
		customerDTO = customerMapper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	@GetMapping("/suma/{n}/{m}")
	public ResponseEntity<?> suma(@PathVariable("n") Integer n, @PathVariable("m") Integer m) throws Exception{
		Integer respuesta = n + m;
		return ResponseEntity.ok().body(respuesta);
	}
}

