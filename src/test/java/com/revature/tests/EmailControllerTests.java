package com.revature.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.client.http.HttpResponse;
import com.revature.config.TemplateConfig;
import com.revature.controller.EmailController;
import com.revature.models.ReservationEmail;
import com.revature.models.TemplatedEmail;
import com.revature.service.EmailService;

/**
 * The Class EmailController. Controller to manage request body to send the
 * appropriate message to the user.
 * 
 * @author 2019/03/2019
 * @author Thomas Reardon
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerTests {

	private MockMvc mockMvc;

	private ObjectMapper om;
	
	@Mock
	HttpResponse httpResponse;

	@Autowired
	TemplateConfig templateConfig;

	@Mock
	EmailService emailService;

	@InjectMocks
	private EmailController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		om = new ObjectMapper();
	}

	// @PostMapping("sendconfirmation")
	@Test
	public void sendConfirmation() throws Exception {
		
		ReservationEmail reservationEmail = new ReservationEmail();
		reservationEmail.setStartTime(LocalDateTime.now());
		reservationEmail.setEndTime(LocalDateTime.now());
		reservationEmail.setBuildingName("building name");

//		TemplatedEmail templateDataTest = new TemplatedEmail(
//				reservationEmail.getStartTime()
//						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
//				reservationEmail.getEndTime()
//						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
//				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(any(), any(),
				any())).thenReturn(om.writeValueAsString(new ResponseEntity<TemplatedEmail>(HttpStatus.CREATED)));

		mockMvc.perform(post("/sendconfirmation").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(reservationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

	/**
	 * Send reminder. Method that listens to a sendreminder end point, grabs the
	 * body of the site and extracts the necessary components to form the email. The
	 * components are the user email, the subject of the message and the message
	 * itself.
	 * 
	 * It's to send a reminder message to the client. this method is dependent with
	 * a schedule service that will check every minute if a user has an appointment
	 * Coming soon.
	 */

	@PostMapping("sendreminder")
	public void sendReminder() {

	}

	/**
	 * Send cancellation. Method that listens to a send cancellation end point,
	 * grabs the body of the site and extracts the necessary components to form the
	 * email. The components are the user email, the subject of the message and the
	 * message itself.
	 * 
	 * Updates DB of the canceled appointment and sends the user an email of their
	 * cancellation.
	 * @throws Exception 
	 * 
	 * @throws JSONException
	 * 
	 */
	
	//@PostMapping("sendcancellation")
	//@Test
	public void sendCancellation() throws Exception {
		
		ReservationEmail reservationEmail = new ReservationEmail("resource.service.dummy@gmail.com",
				LocalDateTime.of(2019, 2, 12, 1, 0), LocalDateTime.of(2019, 2, 12, 2, 0), "Test Building", "Test Room",
				1);
		
		TemplatedEmail templateDataTest = new TemplatedEmail(
				reservationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		
		Mockito.when(emailService.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getConfirmTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(delete("").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(templateDataTest)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

	}

	/**
	 * Send update. Method that listens to a sendupdate end point, grabs the body of
	 * the site and extracts the necessary components to form the email. The
	 * components are the user email, the subject of the message and the message
	 * itself.
	 * 
	 * sends updated information to the user, the update includes reminder changes
	 * or appointment details changes.
	 */
	@PostMapping("sendupdate")
	public void sendUpdate() {

	}

	/**
	 * Send admin confirmation. Method that listens to a sendadminconfirmation end
	 * point, grabs the body of the site and extracts the necessary components to
	 * form the email. The components are the user email, the subject of the message
	 * and the message itself.
	 * 
	 * The user receives an email if the admin has made any changes
	 * @throws Exception 
	 */
	//@PostMapping("sendadminconfirmation")
	//@Test
	public void sendAdminConfirmation() throws Exception {
		
		ReservationEmail verificationEmail = new ReservationEmail("resource.service.dummy@gmail.com",
				LocalDateTime.of(2019, 2, 12, 1, 0), LocalDateTime.of(2019, 2, 12, 2, 0), "Test Building", "Test Room",
				1);
		
		if (verificationEmail.getEmail() != null
				&& verificationEmail.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) 
		{
			
			
			TemplatedEmail templateDataTest = new TemplatedEmail(
					verificationEmail.getStartTime()
							.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
					verificationEmail.getEndTime()
							.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
					verificationEmail.getBuildingName(), verificationEmail.getResourceName());

			
			Mockito.when(emailService.sendTemplatedEmail(verificationEmail.getEmail(), templateConfig.getConfirmTemplate(),
					om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

			mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON)
					.content(om.writeValueAsString(templateDataTest)).accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated());

		}
	}

}
