package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.example.demo.feign.Hospital_Doctor_Feign;
import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;
//import com.example.demo.repository.HospitalRepository;


@RestController
@RequestMapping("api/hospital")
public class HospitalController {
	
	// @Autowired
	// HospitalRepository hospitalRepository;
	
	// @Autowired
	// Hospital_Doctor_Feign feign_client;
	
	List<Doctor> doctors_received = new ArrayList<>();

	@Bean
	public Consumer<String> readDoctors() {
		System.out.println("***********received doctor details**********");

		return (doctor) -> {
			System.out.println("Inside the consumer");
//			doctors_received.add(doctor);
			System.out.println("Consumer Received : " + doctor);
		};
	}
	
	@GetMapping("/hospitals/{hospitalId}")
	ResponseEntity<Hospital> findAllDoctorsInHospitals(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = new Hospital() ;
//		if (hospital != null) {
//			List<Integer> doctor_ids = repo.findDoctorIds(hospitalId);
//			for (int i = 0; i < doctor_ids.size(); i++) {
//				for (Doctor d : doctors_received) {
//					if (d.getDoctorId() == doctor_ids.get(i)) {
//						doctors.add(d);
//					}
//				}
//			}

			hospital.setDoctors(doctors_received);

			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);

//		}
//		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}
	
	/* @GetMapping("/{hospitalId}")
	ResponseEntity<Hospital> findAllDoctorsInHospital(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		if(hospital != null) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Doctor[]> entity = restTemplate.getForEntity("http://localhost:8083/api/doctors/{hospitalId}", Doctor[].class, hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
	} */
	
	/* @GetMapping("/hospitals-feign/{hospitalId}")
	ResponseEntity<Hospital> findAllDoctorsInHospitalFeign(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		if(hospital != null) {
			ResponseEntity<List<Doctor>> entity = feign_client.findAllDoctors_by_hospitalId(hospitalId);
			hospital.setDoctors(entity.getBody());
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
	} */

}
