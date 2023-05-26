package com.payment.service.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigs {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
