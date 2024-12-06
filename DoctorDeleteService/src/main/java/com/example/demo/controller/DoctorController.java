package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable int id) {
	        doctorService.deleteDoctor(id);
	}

}
