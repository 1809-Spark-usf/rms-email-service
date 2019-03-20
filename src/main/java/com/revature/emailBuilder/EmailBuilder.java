package com.revature.emailBuilder;


/**
 * The Interface EmailBuilder.
 * skeleton of the email object itself.
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 */
public interface EmailBuilder {

	/**
	 * Sets the from.
	 * The sender of the email.
	 *
	 * @param from the new sender.
	 */
	void setFrom(String from);
	
	/**
	 * Sets the to.
	 * The destination of the email,
	 * the recipient.
	 *
	 * @param to the new recipient.
	 */
	void setTo(String to);
	
	/**
	 * Sets the subject.
	 * The subject of the email.
	 *
	 * @param subject the new email subject or purpose.
	 */
	void setSubject(String subject);
	
	/**
	 * Sets the body.
	 * The message itself for the 
	 * email object.
	 *
	 * @param body the new message.
	 */
	void setBody(String body);
	
	/**
	 * Send.
	 * method that sends the message to the recipient
	 */
	void send();
	
	void sendTemplatedEmail(String templateName, String templateData);
	
	/**
	 * The getter of the "from" or the
	 * sender of the email.
	 *
	 * @param from the sender of the email.
	 * @return the EmailBuilder object (this.from)
	 */
	EmailBuilder withFrom(String from);
	/**
	 * The getter of the recipient
	 * of the email.
	 *
	 * @param to the recipient of the email.
	 * @return the EmailBuilder object (this.to)
	 */
	EmailBuilder withTo(String to);
	
	/**
	 * The getter for the reason of the message.
	 * 
	 *
	 * @param subject the reason for the message.
	 * @return the EmailBuilder object (this.subject)
	 */
	EmailBuilder withSubject(String subject);
	
	/**
	 * The getter for the actual message
	 * for the email.
	 *
	 * @param body the message of the email.
	 * @return the EmailBuilder object (this.from)
	 */
	EmailBuilder withBody(String body);

}
