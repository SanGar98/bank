package com.vortexbird.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortexbird.bank.doamin.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
