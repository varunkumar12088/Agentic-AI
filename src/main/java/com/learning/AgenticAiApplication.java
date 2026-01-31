package com.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.properties.AIProperties;
import com.learning.util.JsonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({AIProperties.class})
public class AgenticAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgenticAiApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return JsonUtil.mapper;
	}

}
