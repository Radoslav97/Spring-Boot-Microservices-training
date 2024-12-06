package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.pojo.Doctor;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;
import org.springframework.http.MediaType;

import com.example.demo.repository.DoctorRepository;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorFindAllApplicationTests {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private DoctorRepository repo;
	
	@Test
	void contextLoads() {
	}
	
	// junit test case for test controller
	@Test
	public void testfindAllDoctors_by_hospitalId() throws Exception {
		Doctor doctor1 = new Doctor(1, "Dave Smith", "Cardiology" ,101);
		Doctor doctor2 = new Doctor(1, "Alan Lamb", "Neurology" ,101);
		
		List<Doctor> doctors = Arrays.asList(doctor1, doctor2);
		
		when(repo.findByHospitalId(101)).thenReturn(doctors);
		
		mockmvc.perform(get("/doctors/101")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("[0].doctorName", is("Dave Smith")))
				.andExpect(jsonPath("[1].doctorName", is("Alan Lamb")));
				
	}

}
