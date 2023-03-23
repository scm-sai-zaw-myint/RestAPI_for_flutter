package com.scm.flutterdemoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"com.scm.flutterdemoapi"})
@ComponentScan(basePackages = {"com.scm.flutterdemoapi"})
public class FlutterDemoAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlutterDemoAPIApplication.class, args);
	}
}
