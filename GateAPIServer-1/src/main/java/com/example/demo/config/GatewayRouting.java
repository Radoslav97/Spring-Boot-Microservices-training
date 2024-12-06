package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouting {
	
	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("hospital_route", r -> r.path("/api/hospital/hospitals-feign/**").uri("http://localhost:9091/api/hospital/hospitals-feign"))
				.route("doctor_findall_route", r -> r.path("/api/doctors/**").uri("lb://DOCTOR-FIND-ALL/api/doctors"))
				//.route("doctor_route", r -> r.path("/api/doctors/**").filters(f -> f.setRequestHeader("sort", "Medicine")).uri("lb://DOCTOR-FIND-ALL/api/doctors"))
				//.route("doctor_route", r -> r.path("/api/doctors/**").filters(f -> f.setResponseHeader("myresponse", "Response")).uri("lb://DOCTOR-FIND-ALL/api/doctors"))
				.route("doctor_update_route", r -> r.path("/api/doctor/**").uri("lb://DOCTOR-UPDATE-SERVICE/api/doctor/**"))
				//.route("doctor_delete_route", r -> r.path("/api/doctor/**").uri("lb://DOCTOR-SERVICE-DELETE/api/doctor/**"))
				.build();
	}

}
