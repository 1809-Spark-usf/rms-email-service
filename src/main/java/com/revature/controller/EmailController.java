package com.revature.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.EmailService;

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
	
	/** The Email service. */
	EmailService es = new EmailService();
	
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
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("sendconfirmation")
	public void sendConfirmation() throws IOException {
		/* Note: at the moment it sends the same message to the same email */
		es.sendEmail("zxanderrodriguez@gmail.com", "testing this", "it worked!! maybe");
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
	 */
	@PostMapping("sendreminder")
	public void sendReminder() {
		
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
	 */
	@PostMapping("sendcancellation")
	public void sendCancellation() {
	
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
	 */
	@PostMapping("sendadminconfirmation")
	public void sendAdminConfirmation() {
		
	}
	

}
