package com.revature.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.config.TemplateConfig;
import com.revature.controller.EmailController;
import com.revature.models.ReservationEmail;
import com.revature.models.TemplatedEmail;
import com.revature.models.VerificationEmail;
import com.revature.service.EmailService;

/**
 * Email Service Tests
 * 
 * Used to test the email service and
 * confirm that what's being sent by
 * our controller is correct for each
 * end point.
 * 
 * Note that these tests should be run with
 * an email verified with the AWS SES account
 * and should be tested one at a time using the
 * Ignore annotation to skip tests. This is because
 * AWS may have trouble sending multiple emails at 
 * once while the account is in sandbox mode.
 * 
 * @author Austin D. | 1811-Java-Nick | 1/13/19 
 *
 */

@WebMvcTest(EmailController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Import(TemplateConfig.class)
public class EmailServiceTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	TemplateConfig templateConfig;
	
	@MockBean
	private EmailService emailService;
	

	@Test
	@Ignore
	public void sendConfirmationTest() throws JsonProcessingException, Exception {
		
		ReservationEmail testEmail = new ReservationEmail("resource.service.dummy@gmail.com", LocalDateTime.of(2019, 2, 12, 1, 0), LocalDateTime.of(2019, 2, 12, 2, 0), 
				"Test Building", "Test Room", 1);
		TemplatedEmail templateData = new TemplatedEmail(testEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				testEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				testEmail.getBuildingName(), testEmail.getResourceName());
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		om.registerModule(new JavaTimeModule());
		Mockito.when(emailService.sendTemplatedEmail(testEmail.getEmail(), templateConfig.getConfirmTemplate(), om.writeValueAsString(templateData))).thenReturn(om.writeValueAsString(templateData));
		
		ResultActions request = mockMvc.perform(post("/sendconfirmation")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(om.writeValueAsString(testEmail)));
		request.andExpect(status().isOk());
	}
	
	@Test
	@Ignore
	public void sendCancellationTest() throws JsonProcessingException, Exception {
		
		ReservationEmail testEmail = new ReservationEmail("resource.service.dummy@gmail.com", LocalDateTime.of(2019, 2, 12, 1, 0), LocalDateTime.of(2019, 2, 12, 2, 0), 
				"Test Building", "Test Room", 1);
		TemplatedEmail templateData = new TemplatedEmail(testEmail.getStartTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				testEmail.getEndTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				testEmail.getBuildingName(), testEmail.getResourceName());
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		om.registerModule(new JavaTimeModule());
		Mockito.when(emailService.sendTemplatedEmail("resource.service.dummy@gmail.com", "FormattedConfirmationTemplate", om.writeValueAsString(templateData))).thenReturn(om.writeValueAsString(templateData));
		
		ResultActions request = mockMvc.perform(post("/sendcancellation")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(om.writeValueAsString(testEmail)));
		request.andExpect(status().isOk());
	}
	
	@Test
	public void sendAdminConfirmationTest() throws JsonProcessingException, Exception {
		
		VerificationEmail testEmail = new VerificationEmail("resource.service.dummy@gmail.com", "google.com");
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		Mockito.when(emailService.sendTemplatedEmail("resource.service.dummy@gmail.com", "FormattedConfirmationTemplate", om.writeValueAsString(testEmail))).thenReturn(om.writeValueAsString(testEmail));
		
		ResultActions request = mockMvc.perform(post("/sendadminconfirmation")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(om.writeValueAsString(testEmail)));
		request.andExpect(status().isOk());
	}

}
