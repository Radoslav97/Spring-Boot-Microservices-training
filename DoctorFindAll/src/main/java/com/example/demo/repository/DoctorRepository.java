package com.example.demo.repository;

import com.example.demo.pojo.Doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	List<Doctor> findByHospitalId(int hospital_id);

}
