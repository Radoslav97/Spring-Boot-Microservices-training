package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.feign.Hospital_Doctor_Feign;
import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;
import com.example.demo.repository.HospitalRepository;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("api/hospital")
public class HospitalController {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	Hospital_Doctor_Feign feign_client;
	
	//RestTemplate
	@GetMapping("/{hospitalId}")
	@CircuitBreaker(name="circuit-breaker-for-doctor", fallbackMethod="mymethod")
	ResponseEntity<Hospital> findAllDoctorsInHospital(@PathVariable int hospitalId) {
		System.out.println("hit the consumer service");
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		System.out.println("Found Hospital::::::::: " + hospitalId);
		if(hospital != null) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Doctor[]> entity = restTemplate.getForEntity("http://localhost:8083/api/doctors/{hospitalId}", Doctor[].class, hospitalId);
			//ResponseEntity<Doctor[]> entity = restTemplate.getForEntity("http://doctor-find-all/api/doctors/{hospitalId}", Doctor[].class, hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
	}
	
	//Feign Client
	@GetMapping("/hospitals-feign/{hospitalId}")
	@CircuitBreaker(name="circuit-breaker-for-doctor", fallbackMethod="mymethod")
	ResponseEntity<Hospital> findAllDoctorsInHospital_feign(@PathVariable int hospitalId) {
		System.out.println("hit the consumer service");
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		System.out.println("Found Hospital::::::::: " + hospitalId);
		if(hospital != null) {
			ResponseEntity<List<Doctor>> entity = feign_client.findAllDoctors_by_hospitalId(hospitalId);
			hospital.setDoctors(entity.getBody());
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
		
	}
	
	ResponseEntity<Hospital> mymethod(int hospitalId, Throwable e) {
		Hospital hospital = new Hospital();
		hospital.setHospitalRegistrationId(1);
		hospital.setHospitalName("default");
		hospital.setAddress("default-address");
		hospital.setDoctors(null);
		
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		// return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);	}
	}
	
	
	//Feign Client
	@GetMapping("/hospitals-feign-retry/{hospitalId}")
	@Retry(name="retry-for-doctor") //, fallbackMethod="mymethod")
	ResponseEntity<Hospital> findAllDoctorsInHospital_feign_retry(@PathVariable int hospitalId) {
		System.out.println("hit the consumer service");
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		System.out.println("Found Hospital::::::::: " + hospitalId);
		if(hospital != null) {
			ResponseEntity<List<Doctor>> entity = feign_client.findAllDoctors_by_hospitalId(hospitalId);
			hospital.setDoctors(entity.getBody());
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
			
	}
	
	
	@GetMapping("/hospitals-rate-limiter/{hospitalId}")
	@RateLimiter(name="rate-limiter-for-doctor", fallbackMethod="getDoctorFallback_rateLimiter")
	ResponseEntity<Hospital> findAllDoctorsInHospital_feign_rateLimitter(@PathVariable int hospitalId) {
		System.out.println("hit the consumer service");
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		System.out.println("Found Hospital::::::::: " + hospitalId);
		if(hospital != null) {
			ResponseEntity<List<Doctor>> entity = feign_client.findAllDoctors_by_hospitalId(hospitalId);
			hospital.setDoctors(entity.getBody());
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
			
	}
	
	ResponseEntity<Hospital> getDoctorFallback_rateLimiter(int hospitalId, Throwable e) {
		return new ResponseEntity<Hospital>(HttpStatus.TOO_MANY_REQUESTS);
	}
		
	@GetMapping("/hospitals-bulk-head/{hospitalId}")
	@Bulkhead(name="bulk-head-for-doctor", fallbackMethod="getDoctorFallback_bulkhead")
	ResponseEntity<Hospital> findAllDoctorsInHospital_feign_bulkhead(@PathVariable int hospitalId) {
		System.out.println("hit the consumer service");
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
		System.out.println("Found Hospital::::::::: " + hospitalId);
		if(hospital != null) {
			ResponseEntity<List<Doctor>> entity = feign_client.findAllDoctors_by_hospitalId(hospitalId);
			hospital.setDoctors(entity.getBody());
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		} else {
			return new ResponseEntity<Hospital>(hospital, HttpStatus.NO_CONTENT);
		}
				
	}
	
	ResponseEntity<Hospital> getDoctorFallback_bulkhead(int hospitalId, Throwable e) {
		return new ResponseEntity<Hospital>(HttpStatus.TOO_MANY_REQUESTS);
	}

}
