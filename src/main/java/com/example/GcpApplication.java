package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class GcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpApplication.class, args);


	}


}
