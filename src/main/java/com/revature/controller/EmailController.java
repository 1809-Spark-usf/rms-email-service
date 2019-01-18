package com.revature.controller;

import java.io.IOException;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.models.ReservationEmail;
import com.revature.models.TemplatedEmail;
import com.revature.models.VerificationEmail;
import com.revature.service.EmailService;
import com.revature.config.TemplateConfig;

/**
 * The Class EmailController.
 * Controller to manage request
 * body to send the appropriate 
 * message to the user.
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 */
@RestController
@RequestMapping("")
public class EmailController {
	
	/** The template config. */
	@Autowired
	TemplateConfig templateConfig;

	/** The Email service. */
	EmailService es = new EmailService();
	
	/** The remider URI.*/
	@Value("${RMS_REMINDER_URL:http://localhost:8080/reminder/}")
	String emailUri;
	
	/**
	 * Send confirmation.
	 * Method that listens to a sendconfirmation 
	 * end point, grabs the body of the site and
	 * extracts the necessary components to form
	 * the email. 
	 * The components are the user email, the 
	 * subject of the message and the message itself. 
	 * 
	 * It's only for confirming the users appointment.
	 *
	 * @param reservationEmail the reservation email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("sendconfirmation")
	public void sendConfirmation(@RequestBody ReservationEmail reservationEmail) throws IOException{
		
		/*Sends the ReservationEmail Object to the EmailService
		 *that will go through amazon authorization and send the
		 *created email.*/
		TemplatedEmail templateData = new TemplatedEmail(reservationEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());
		
		es.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getConfirmTemplate(), new ObjectMapper().writeValueAsString(templateData));
				
		/* Sending the object to another method so it can send it to the reminder service*/
		sendToReminderService(reservationEmail);
	}
	
	/**
	 *A fallback method for use with Hystrix using the Circuit Breaker pattern. If
	 * the email service fails or if it is down, the postConfirmation method will be
	 * replaced with this method call and the reservation service will still be able
	 * to run. Loosely couples the two services.
	 *
	 * @param reservationEmail the reservation email
	 */
	@HystrixCommand(fallbackMethod = "emailFallback")
	public void sendToReminderService(ReservationEmail reservationEmail) {
		
		/* Creates the rest template to send the object to that URL (AKA reminder service) */
		new RestTemplate().postForLocation(URI.create(emailUri + "newreminder"), reservationEmail);
	}
	
	/**
	 * Email fallback.
	 *
	 * @param reservation the reservation
	 */
	@SuppressWarnings("unused")
	private void emailFallback(ReservationEmail reservation) {
		
		System.out.println("fail to send to reminder");
	}

	/**
	 * Send reminder.
	 * Method that listens to a sendreminder 
	 * end point, grabs the body of the site and
	 * extracts the necessary components to form
	 * the email. 
	 * The components are the user email, the 
	 * subject of the message and the message itself. 
	 * 
	 * It's to send a reminder message to the client.
	 * this method is dependent with a schedule service
	 * that will check every minute if a user has an appointment
	 * Coming soon.
	 *
	 * @param reservationEmail the reservation email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("sendreminder")
	public void sendReminder(@RequestBody ReservationEmail reservationEmail) throws IOException {
	
		/*Sends the ReservationEmail Object to the EmailService
		 *that will go through amazon authorization and send the
		 *created email.*/
		System.out.println("Sending the reminder");
		TemplatedEmail templateData = new TemplatedEmail(reservationEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());	
		
		es.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getReminderTemplate(), new ObjectMapper().writeValueAsString(templateData));
				
	}
	
	/**
	 * Send cancellation.
	 * Method that listens to a send cancellation
	 * end point, grabs the body of the site and
	 * extracts the necessary components to form
	 * the email. 
	 * The components are the user email, the 
	 * subject of the message and the message itself. 
	 * 
	 * Updates DB of the canceled appointment
	 * and sends the user an email of their 
	 * cancellation.
	 *
	 * @param reservationEmail the reservation email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("sendcancellation")
	public void sendCancellation(@RequestBody ReservationEmail reservationEmail) throws IOException {
		
		TemplatedEmail templateData = new TemplatedEmail(reservationEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());
		
		es.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getCancelTemplate(), new ObjectMapper().writeValueAsString(templateData));
	}
	
	/**
	 * Send update.
	 * Method that listens to a sendupdate 
	 * end point, grabs the body of the site and
	 * extracts the necessary components to form
	 * the email. 
	 * The components are the user email, the 
	 * subject of the message and the message itself.
	 * 
	 * sends updated information to the user, the update
	 * includes reminder changes or appointment details
	 * changes.
	 * 
	 * (not included yet)
	 */
	@PostMapping("sendupdate")
	public void sendUpdate() {
	
	}
	
	/**
	 * Send admin confirmation.
	 * Method that listens to a sendadminconfirmation
	 * end point, grabs the body of the site and
	 * extracts the necessary components to form
	 * the email. 
	 * The components are the user email, the 
	 * subject of the message and the message itself. 
	 * 
	 * The user receives an email if the admin has made 
	 * any changes
	 *
	 * @param verificationEmail the verification email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("sendadminconfirmation")
	public void sendAdminConfirmation(@RequestBody VerificationEmail verificationEmail) throws IOException{
		if(verificationEmail.getAdminEmail() !=null && verificationEmail.getAdminEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))	
			es.sendTemplatedEmail(verificationEmail.getAdminEmail(), templateConfig.getVerifyTemplate(), new ObjectMapper().writeValueAsString(verificationEmail));
	}
	

}
