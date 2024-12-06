package com.example.demo.service;

import com.example.demo.exception.DoctorException;
import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor updateDoctor(int id, Doctor updatedDoctor) {
		Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if(existingDoctor.isPresent()) {
        	Doctor doctor = existingDoctor.get();
            doctor.setDoctorName(updatedDoctor.getDoctorName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setHospitalId(updatedDoctor.getHospitalId());
            return doctorRepository.save(doctor);
		} else {
			throw new DoctorException("customer with this id is not found");
		}
        
    }

}
