package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DoctorDAO;
import com.example.demo.exception.DoctorAlreadyExistsException;
import com.example.demo.pojo.Doctor;

@RequestMapping("api/doctor")
@RestController
public class DoctorController {

	@Autowired
	DoctorDAO doctorDAO;
	
	@PostMapping
	public Doctor createNewDoctor(@RequestBody Doctor doctor) throws DoctorAlreadyExistsException {
		int added = 0;
		added = doctorDAO.addDoctor(doctor);
		if(added == 1) {
			return doctor;
		}
		return null;
	}
	
}
