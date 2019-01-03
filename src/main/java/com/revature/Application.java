package com.revature;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.revature.service.EmailService;

/**
 * The Class Application.
 * This micro service handles sending
 * confirmation/cancellation/changed email
 * to the user
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 * 
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

}

