package com.munendras.taskprojectlatest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TaskprojectlatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskprojectlatestApplication.class, args);
	}
	
	@Configuration
	public class ModelMapperConfig {

	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }
	}

}
