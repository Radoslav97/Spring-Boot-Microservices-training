package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.Doctor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterServiceCommunicationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private RestTemplate testRestTemplate;
	
	private MockRestServiceServer mockServer;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void testCallMicroservice() throws JsonProcessingException {
		mockServer = MockRestServiceServer.createServer(testRestTemplate);

        Doctor doctor1 = new Doctor(1, "Dave Smith", "Cardiology", 101);
        Doctor doctor2 = new Doctor(2, "Alan Lamb", "Neurology", 101);

        List<Doctor> doctors = Arrays.asList(doctor1, doctor2);

        String doctorsJson = objectMapper.writeValueAsString(doctors);

        mockServer.expect(requestTo("http://localhost:8083/api/doctors/1"))
                .andRespond(withSuccess(doctorsJson, MediaType.APPLICATION_JSON));

        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/api/doctors/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Doctor> actualDoctors = objectMapper.readValue(response.getBody(),
                new TypeReference<List<Doctor>>() {});

        assertThat(actualDoctors).isEqualTo(doctors);
    }

	
}
