package com.ugromart.platform;

import com.ugromart.platform.order.OrderProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class UgromartApplication {

	public static void main(String[] args) {

		SpringApplication.run(UgromartApplication.class, args);
	}

}
