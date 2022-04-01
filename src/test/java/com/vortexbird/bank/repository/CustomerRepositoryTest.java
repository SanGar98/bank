package com.vortexbird.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

//import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.bank.doamin.Customer;
import com.vortexbird.bank.doamin.DocumentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)

class CustomerRepositoryTest {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	void debeCrearUnCustomer() {
		//Arrange
		
		Customer customer = new Customer();
		DocumentType documentType = null;
		customer.setAccounts(null);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setCustId(2020);
		customer.setEmail("homerojsimpson@gmail.com");
		customer.setEnable(true);
		customer.setName("Homero J Simpson");
		customer.setPhone("5555555");
		customer.setRegisteredAccounts(null);
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		Optional<DocumentType> documentTypeOptional=documentTypeRepository.findById(1);
		if(documentTypeOptional.isPresent()==true) {
			documentType=documentTypeOptional.get();
		}
		customer.setDocumentType(documentType);
		
		//Act
		customerRepository.save(customer);
		
		//assert
		assertNotNull(customer);
	}
	
	@Test
	@Order(2)
	@Transactional(readOnly=true)
	void debeConsultarUnCustomer() {
		//Arrange
		Optional<Customer> customerOptional=null;
		
		//Act
		customerOptional = customerRepository.findById(2020);
		
		//Assert
		assertTrue(customerOptional.isPresent());
	}
	
	@Test
	@Order(3)
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	void debeModificarUnCustomer() {
		//Arrange
		Optional<Customer> customerOptional=null;
		Customer customer = null;
		customerOptional = customerRepository.findById(2020);
		assertTrue(customerOptional.isPresent());
		
		customer = customerOptional.get();
		customer.setEnable(false);
		//Act
		customerRepository.save(customer);
		
		//Assert
		assertFalse(customer.getEnable());
	}
	
	@Test
	@Order(4)
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	void debeBorrarUnCustomer() {
		//Arrange
		Optional<Customer> customerOptional=null;
		Customer customer = null;
		customerOptional = customerRepository.findById(2020);
		assertTrue(customerOptional.isPresent());
		customer = customerOptional.get();
		
		//Act
		customerRepository.delete(customer);
	}

}
