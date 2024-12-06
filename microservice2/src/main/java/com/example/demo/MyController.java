package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MyConfiguration;

@RestController
public class MyController {

	
	@Autowired
	MyConfiguration config;
	
	@GetMapping("/")
	public String getData() {
		return "Hello from: " + config.getName() + ", you can connect to me on : " + config.getPhonenumber();
	}
}
