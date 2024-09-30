package com.example.project2;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.PrintStream;

@SpringBootApplication
public class Project2Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Project2Application.class);
		app.run(args);
	}

}
