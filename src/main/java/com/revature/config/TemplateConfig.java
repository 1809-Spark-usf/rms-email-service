package com.revature.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * The TemplateConfig Class
 * Used to provide the template names
 * to the email service. Used for if
 * these template names change so that
 * templates can be changed from the
 * config service instead of through
 * the email service's code.
 * 
 * @author Austin D. | 1811-Java-Nick | 01/08/2019 
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class TemplateConfig {

	/** The cancel template. */
	private String cancelTemplate;
	
	/** The confirm template. */
	private String confirmTemplate;
	
	/** The verify template. */
	private String verifyTemplate;
	
	/** The reminder template. */
	private String reminderTemplate;
	
	
	/**
	 * Gets the reminder template.
	 *
	 * @return the reminder template
	 */
	public String getReminderTemplate() {
		return reminderTemplate;
	}
	
	/**
	 * Sets the reminder template.
	 *
	 * @param reminderTemplate the new reminder template
	 */
	public void setReminderTemplate(String reminderTemplate) {
		this.reminderTemplate = reminderTemplate;
	}
	
	/**
	 * Gets the cancel template.
	 *
	 * @return the cancel template
	 */
	public String getCancelTemplate() {
		return cancelTemplate;
	}
	
	/**
	 * Sets the cancel template.
	 *
	 * @param cancelTemplate the new cancel template
	 */
	public void setCancelTemplate(String cancelTemplate) {
		this.cancelTemplate = cancelTemplate;
	}
	
	/**
	 * Gets the confirm template.
	 *
	 * @return the confirm template
	 */
	public String getConfirmTemplate() {
		return confirmTemplate;
	}
	
	/**
	 * Sets the confirm template.
	 *
	 * @param confirmTemplate the new confirm template
	 */
	public void setConfirmTemplate(String confirmTemplate) {
		this.confirmTemplate = confirmTemplate;
	}
	
	/**
	 * Gets the verify template.
	 *
	 * @return the verify template
	 */
	public String getVerifyTemplate() {
		return verifyTemplate;
	}
	
	/**
	 * Sets the verify template.
	 *
	 * @param verifyTemplate the new verify template
	 */
	public void setVerifyTemplate(String verifyTemplate) {
		this.verifyTemplate = verifyTemplate;
	}
	
	
	/* 
	 */
	@Override
	public String toString() {
		return "TemplateConfig [cancelTemplate=" + cancelTemplate + ", confirmTemplate=" + confirmTemplate
				+ ", verifyTemplate=" + verifyTemplate + ", reminderTemplate=" + reminderTemplate + "]";
	}
	
	/**
	 * Instantiates a new template config.
	 *
	 * @param cancelTemplate the cancel template
	 * @param confirmTemplate the confirm template
	 * @param verifyTemplate the verify template
	 * @param reminderTemplate the reminder template
	 */
	public TemplateConfig(String cancelTemplate, String confirmTemplate, String verifyTemplate,
			String reminderTemplate) {
		super();
		this.cancelTemplate = cancelTemplate;
		this.confirmTemplate = confirmTemplate;
		this.verifyTemplate = verifyTemplate;
		this.reminderTemplate = reminderTemplate;
	}
	
	/**
	 * Instantiates a new template config.
	 */
	public TemplateConfig() {
		super();
	}
	
}
