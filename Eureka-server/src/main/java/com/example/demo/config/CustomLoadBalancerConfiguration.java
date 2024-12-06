/* package com.example.demo.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomLoadBalancerConfiguration {
	
	//based on the earlier execution preference
	@Bean
	public ServiceInstanceListSupplier provideSameInstance(ConfigurableApplicationContext context) {
		
		return ServiceInstanceListSupplier.builder()
				.withBlockingDiscoveryClient().withSameInstancePreference()
				.build(context);
	}
	
	//based on the the zone
	@Bean
	public ServiceInstanceListSupplier zoneBasedInstance(ConfigurableApplicationContext context) {
			
		return ServiceInstanceListSupplier.builder()
					.withDiscoveryClient().withZonePreference()
					.build(context);
	}
	
	//based on weight
	@Bean
	public ServiceInstanceListSupplier withWeightService(ConfigurableApplicationContext context) {
				
		return ServiceInstanceListSupplier.builder()
					.withDiscoveryClient().withWeighted().withCaching()
					.build(context);
	}
		
	//based on health Check
	@Bean
	public ServiceInstanceListSupplier withHealthCheck(ConfigurableApplicationContext context) {
						
		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withHealthChecks()
								.build(context);
	}

} */
