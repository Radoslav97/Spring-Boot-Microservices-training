package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.DoctorAlreadyExistsException;
import com.example.demo.pojo.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		int added = 0;
		String insert_doctor = "insert into doctor_new values(?,?,?,?)";
		try {
			added = jdbcTemplate.update(insert_doctor, doctor.getDoctorId(), doctor.getDoctorName(), doctor.getHospitalId(), doctor.getSpecialization());
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new DoctorAlreadyExistsException("Record Exists");
		}
		return added;
	}

}
