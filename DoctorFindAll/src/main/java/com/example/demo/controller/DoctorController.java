package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;


@RestController
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;

	
	@GetMapping("/api/doctors")
	public List<Doctor> getAllDoctors(@RequestHeader(name="sort", defaultValue = "all") String sort) {
		System.out.println("Reuest Header is: " + sort);
		return doctorService.getAllCustomer();
	}

	@GetMapping("/api/doctors/{hospitalId}")
	public ResponseEntity<List<Doctor>> findAllDoctors_by_hospitalId(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		
		doctors = doctorService.findAllDoctors_by_hospitalId(hospitalId);
		if(doctors.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		}
		return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
	}
}
