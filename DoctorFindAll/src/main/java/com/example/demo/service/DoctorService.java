package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getAllCustomer() {
		return doctorRepository.findAll();
	}
	
	public List<Doctor> findAllDoctors_by_hospitalId(int hospital_id) {
		
		return doctorRepository.findByHospitalId(hospital_id);
		
	}

}
