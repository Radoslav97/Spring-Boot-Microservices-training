package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.pojo.Doctor;

/*@FeignClient(name = "doctor-find-all", url = "http://localhost:8083")
public interface Hospital_Doctor_Feign {
	
	@GetMapping("/api/doctors/{hospitalId}")
	public ResponseEntity<List<Doctor>> findAllDoctors_by_hospitalId(@PathVariable int hospitalId);

} */

@FeignClient(name = "doctor-find-all")		// using Eureka server
public interface Hospital_Doctor_Feign {
	
	@GetMapping("/api/doctors/{hospitalId}")
	public ResponseEntity<List<Doctor>> findAllDoctors_by_hospitalId(@PathVariable int hospitalId);

}
