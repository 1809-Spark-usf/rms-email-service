package com.revature.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.config.TemplateConfig;
import com.revature.models.ReservationEmail;
import com.revature.models.TemplatedEmail;
import com.revature.models.VerificationEmail;
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
	
	@Autowired
	TemplateConfig templateConfig;

	
	/** The Email service. */
	@Autowired
	EmailService es;
	
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
	 * @throws JSONException 
	 */
	@PostMapping("sendconfirmation")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendConfirmation(@RequestBody ReservationEmail reservationEmail) throws IOException{
//		System.out.println("MADE IT HERE INTO THE FUNCTION.");
		
		/*Sends the ReservationEmail Object to the EmailRepositoryService
		 * service that will save it to the Database*/
		
		/*Sends the ReservationEmail Object to the EmailService
		 *that will go through amazon authorization and send the
		 *created email.*/
		TemplatedEmail templateData = new TemplatedEmail(reservationEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());		
		es.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getConfirmTemplate(), new ObjectMapper().writeValueAsString(templateData));
		
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
	@ResponseStatus(HttpStatus.CREATED)
	public void sendReminder(@RequestBody ReservationEmail reservationEmail) throws IOException{
		
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
	 * @throws IOException 
	 * @throws JSONException 
	 * 
	 */
	@PostMapping("sendcancellation")
	@ResponseStatus(HttpStatus.CREATED)
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
	 */
	@PostMapping("sendupdate")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendUpdate(@RequestBody ReservationEmail reservationEmail) throws IOException {
		
		TemplatedEmail templateData = new TemplatedEmail(reservationEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());		
		es.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getUpdateTemplate(), new ObjectMapper().writeValueAsString(templateData));
	
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
	@ResponseStatus(HttpStatus.CREATED)
	public void sendAdminConfirmation(@RequestBody VerificationEmail verificationEmail) throws IOException{
		if(verificationEmail.getAdminEmail() !=null && verificationEmail.getAdminEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))	
			es.sendTemplatedEmail(verificationEmail.getAdminEmail(), templateConfig.getVerifyTemplate(), new ObjectMapper().writeValueAsString(verificationEmail));
	}
	

}
