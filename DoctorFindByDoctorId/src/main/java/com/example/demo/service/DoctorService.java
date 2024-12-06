package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DoctorException;
import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public Optional<Doctor> getDoctorById(int id) {
		Optional<Doctor> customer = doctorRepository.findById(id);
		if(!customer.isPresent()) {
			throw new DoctorException("customer with this id is not found");
		}
		return customer;
	}

}
