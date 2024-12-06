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
	
	public void deleteDoctor(int doctorId) {
	    if (doctorRepository.existsById(doctorId)) {
	        doctorRepository.deleteById(doctorId);
	    } else {
	        throw new DoctorException("Doctor not found with id: " + doctorId);
	    }
	}

}
