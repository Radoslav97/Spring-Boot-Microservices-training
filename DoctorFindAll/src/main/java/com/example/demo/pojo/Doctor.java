package com.example.demo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_new")
public class Doctor {
	
	@Id
	@Column(name="doctorid")
	private int doctorId;
	@Column(name="doctorname")
	private String doctorName;
	private String specialization;
	@Column(name="hospitalid")
	private int hospitalId;
	
	public Doctor(int doctorId, String doctorName, String specialization, int hospitalId) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.hospitalId = hospitalId;
	}
	
	

	public Doctor() {
		super();
	}



	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
	
	

}
