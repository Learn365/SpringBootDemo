package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner  {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Value("${spring.application.name:demoservice}")
	private String name;

	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is an error message");

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@RequestMapping(value="/")
	public String helo(){
		return "Hello World";
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World from Command Line Runner");
	}

	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Hello World from Application Runner");
	}
}
