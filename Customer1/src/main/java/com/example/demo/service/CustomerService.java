package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.pojo.Customer;

@Service
public class CustomerService {
	
	private List<Customer> customers = new ArrayList();


	public CustomerService() {
		customers = new ArrayList<>(Arrays.asList(customerArray));
	}


	Customer customerArray[] = {
			new Customer(1L, "customer1", "customer1@gmail.com"),
			new Customer(2L, "customer2", "customer2@gmail.com"),
	};
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Optional<Customer> findById(Long id) {
		return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
	}
	
	public Customer saveCustomer(Customer customer) {
		customers.add(customer);
		return customer;
	}
	
	public void deleteById(Long id) {
		customers.removeIf(c -> c.getId().equals(id));
	}
	
	public Customer update(Long id, Customer customer) {
		Optional<Customer> existingCustomer = findById(id);
		
		if(existingCustomer.isPresent()) {
			existingCustomer.get().setName(customer.getName());
			existingCustomer.get().setEmail(customer.getEmail());
			return existingCustomer.get();
		}
		throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer not found with id " + id
        );
	}
}
