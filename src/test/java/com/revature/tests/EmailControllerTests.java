package com.revature.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
	TemplateConfig templateConfig;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private EmailController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		om = new ObjectMapper();
		// allow objectmapper to map LocalDateTime correctly
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	// @PostMapping("sendconfirmation")
	@Test
	public void sendConfirmation() throws Exception {

		ReservationEmail reservationEmail = new ReservationEmail();
		reservationEmail.setStartTime(LocalDateTime.now());
		reservationEmail.setEndTime(LocalDateTime.now());
		reservationEmail.setBuildingName("building name");
		reservationEmail.setEmail("user@gmail.com");

		TemplatedEmail templateDataTest = new TemplatedEmail(
				reservationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getConfirmTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(post("/sendconfirmation").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(reservationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

	/**
	 * Send cancellation. Method that listens to a send cancellation end point,
	 * grabs the body of the site and extracts the necessary components to form the
	 * email. The components are the user email, the subject of the message and the
	 * message itself.
	 * 
	 * Updates DB of the canceled appointment and sends the user an email of their
	 * cancellation.
	 * 
	 * @throws Exception
	 * 
	 * @throws JSONException
	 * 
	 */

	// @PostMapping("sendcancellation")
	@Test
	public void sendCancellation() throws Exception {

		ReservationEmail reservationEmail = new ReservationEmail();
		reservationEmail.setStartTime(LocalDateTime.now());
		reservationEmail.setEndTime(LocalDateTime.now());
		reservationEmail.setBuildingName("building name");
		reservationEmail.setEmail("user@gmail.com");

		TemplatedEmail templateDataTest = new TemplatedEmail(
				reservationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getCancelTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(post("/sendcancellation").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(reservationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

	/**
	 * Send admin confirmation. Method that listens to a sendadminconfirmation end
	 * point, grabs the body of the site and extracts the necessary components to
	 * form the email. The components are the user email, the subject of the message
	 * and the message itself.
	 * 
	 * The user receives an email if the admin has made any changes
	 * 
	 * @throws Exception
	 */
	// @PostMapping("sendadminconfirmation")
	@Test
	public void sendAdminConfirmation() throws Exception {

		ReservationEmail verificationEmail = new ReservationEmail("resource.service.dummy@gmail.com",
				LocalDateTime.of(2019, 2, 12, 1, 0), LocalDateTime.of(2019, 2, 12, 2, 0), "Test Building", "Test Room",
				1);

		TemplatedEmail templateDataTest = new TemplatedEmail(
				verificationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				verificationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				verificationEmail.getBuildingName(), verificationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(verificationEmail.getEmail(), templateConfig.getConfirmTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(post("/sendadminconfirmation").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(verificationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

	
	// @PostMapping("sendreminder")
	@Test
	public void sendReminder() throws Exception {

		ReservationEmail reservationEmail = new ReservationEmail();
		reservationEmail.setStartTime(LocalDateTime.now());
		reservationEmail.setEndTime(LocalDateTime.now());
		reservationEmail.setBuildingName("building name");
		reservationEmail.setEmail("user@gmail.com");

		TemplatedEmail templateDataTest = new TemplatedEmail(
				reservationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getCancelTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(post("/sendreminder").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(reservationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

	// @PostMapping("sendupdate")
	@Test
	public void sendUpdate() throws Exception {

		ReservationEmail reservationEmail = new ReservationEmail();
		reservationEmail.setStartTime(LocalDateTime.now());
		reservationEmail.setEndTime(LocalDateTime.now());
		reservationEmail.setBuildingName("building name");
		reservationEmail.setEmail("user@gmail.com");

		TemplatedEmail templateDataTest = new TemplatedEmail(
				reservationEmail.getStartTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getEndTime()
						.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)),
				reservationEmail.getBuildingName(), reservationEmail.getResourceName());

		Mockito.when(emailService.sendTemplatedEmail(reservationEmail.getEmail(), templateConfig.getCancelTemplate(),
				om.writeValueAsString(templateDataTest))).thenReturn(om.writeValueAsString(templateDataTest));

		mockMvc.perform(post("/sendupdate").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(reservationEmail)).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());

	}

}
