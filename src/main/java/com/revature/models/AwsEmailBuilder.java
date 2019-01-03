package com.revature.models;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.revature.emailBuilder.EmailBuilder;

/**
 * The Class AwsEmailBuilder.
 * Class that implements the EmailBuilder
 * to build an email object and add in
 * logic to the methods.
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 */
public class AwsEmailBuilder implements EmailBuilder {

	/** The variable for the components of an email */
	String from,to,subject,body;
	
	/** The simple email service. */
	private AmazonSimpleEmailService simpleEmailService;
	
	/**
	 * Instantiates a new AWS email builder.
	 *
	 * @param simpleEmailService the AWS SES connection.
	 */
	public AwsEmailBuilder(AmazonSimpleEmailService simpleEmailService) {
		this.simpleEmailService = simpleEmailService;
	}
	/* The sender of the email.
	 *
	 * @param from the new sender.
	 */
	public void setFrom(String from) {
		this.from = from;
		
	}

	/* Sets the to.
	 * The destination of the email,
	 * the recipient.
	 *
	 * @param to the new recipient.
	 */
	public void setTo(String to) {
		this.to= to;
		
	}

	/* Sets the subject.
	 * The subject of the email.
	 *
	 * @param subject the new email subject or purpose.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
		
	}

	/* Sets the body.
	 * The message itself for the 
	 * email object.
	 *
	 * @param body the new message.
	 */
	public void setBody(String body) {
		this.body = body;
		
	}

	/**
	 * Gets the to as list.
	 *
	 * @return the to as list
	 */
	private List<String> getToAsList() {
		return Arrays.asList(to.split(","));
	}
	/* Send.
	 * Method that sends the message to the recipient.
	 * Destination: is where the email is going to be sent to.
	 * SendEmailRequest: makes the email to send to the AWS email service.
	 * 
	 */
	public void send() {
		Destination destination = new Destination(getToAsList());
		SendEmailRequest request = new SendEmailRequest(from, 
														destination,
														createMessage());
		simpleEmailService.sendEmail(request);
		
	}

	/**
	 * Creates the message.
	 * Writes the purpose and content
	 * of the email itself.
	 *
	 * @return the message
	 */
	private Message createMessage() {
		Body ambody = new Body(new Content(body));
		Message message = new Message(new Content(subject),ambody);
		return message;
	}
	/* The getter of the "from" or the
	 * sender of the email.
	 *
	 * @param from the sender of the email.
	 * @return the EmailBuilder object (this.from)
	 */
	public EmailBuilder withFrom(String from) {
		this.from = from;
		return this;
	}

	/* The getter of the recipient
	 * of the email.
	 *
	 * @param to the recipient of the email.
	 * @return the EmailBuilder object (this.to)
	 */
	public EmailBuilder withTo(String to) {
		this.to= to;
		return this;
	}

	/*  The getter for the reason of the message.
	 * 
	 *
	 * @param subject the reason for the message.
	 * @return the EmailBuilder object (this.subject
	 */
	public EmailBuilder withSubject(String subject) {
		this.subject = subject;
		return this;
	}

	/* The getter for the actual message
	 * for the email.
	 *
	 * @param body the message of the email.
	 * @return the EmailBuilder object (this.from)
	 */
	public EmailBuilder withBody(String body) {
		this.body = body;
		return this;
	}
}
