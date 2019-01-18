package com.revature.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.models.ReservationEmail;
/**
 * Reservation Email Service.
 * It will grab the body already passed
 * in this class and send it to another microservice
 * 
 * @author 1811-Java-Nick | 01/08/2019
 *
 */
@Service
public class ReservationEmailService {

	/*change url to reminder endpoint*/
	@Value("${RMS_EMAIL_URL:http://localhost:8080/email/}")
	String emailUri;
	
	
	/**
	 * Posts a ReservationEmail object to the Email service in order to send a
	 * confirmation email to the user
	 * 
	 * @param reservation
	 * @param resource
	 * @author Austin D. | 1811-Java-Nick | 01/04/2019
	 */
	@HystrixCommand(fallbackMethod = "emailFallback")
	public void sendReservationEmail(ReservationEmail reservation) {
		
		new RestTemplate().postForLocation(URI.create(emailUri + "newreminder"), reservation);
	}

	/**
	 * A fallback method for use with Hystrix using the Circuit Breaker pattern. If
	 * the email service fails or if it is down, the postConfirmation method will be
	 * replaced with this method call and the reservation service will still be able
	 * to run. Loosely couples the two services.
	 * 
	 * @param reservation
	 * @param resource
	 */
	@SuppressWarnings("unused")
	private void emailFallback(ReservationEmail reservation) {
	}
}
