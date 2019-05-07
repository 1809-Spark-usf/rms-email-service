package com.revature.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The TemplateConfig Class
 * Used to provide the template names
 * to the email service. Used for if
 * these template names change so that
 * templates can be changed from the
 * config service instead of through
 * the email service's code.
 * @author Austin D. 1811-Java-Nick 1/8/19 
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class TemplateConfig {

	private String cancelTemplate;
	private String confirmTemplate;
	private String verifyTemplate;
	private String reminderTemplate;
	private String updateTemplate;
	
	public String getCancelTemplate() {
		return cancelTemplate;
	}

	public void setCancelTemplate(String cancelTemplate) {
		this.cancelTemplate = cancelTemplate;
	}

	public String getConfirmTemplate() {
		return confirmTemplate;
	}

	public void setConfirmTemplate(String confirmTemplate) {
		this.confirmTemplate = confirmTemplate;
	}

	public String getVerifyTemplate() {
		return verifyTemplate;
	}

	public void setVerifyTemplate(String verifyTemplate) {
		this.verifyTemplate = verifyTemplate;
	}

	public String getReminderTemplate() {
		return reminderTemplate;
	}

	public void setReminderTemplate(String reminderTemplate) {
		this.reminderTemplate = reminderTemplate;
	}

	public String getUpdateTemplate() {
		return updateTemplate;
	}

	public void setUpdateTemplate(String updateTemplate) {
		this.updateTemplate = updateTemplate;
	}
	
	@Override
	public String toString() {
		return "TemplateConfig [cancelTemplate=" + cancelTemplate + ", confirmTemplate=" + confirmTemplate
				+ ", verifyTemplate=" + verifyTemplate + ", reminderTemplate=" + reminderTemplate + ", updateTemplate="
				+ updateTemplate + "]";
	}

	public TemplateConfig(String cancelTemplate, String confirmTemplate, String verifyTemplate, String reminderTemplate,
			String updateTemplate) {
		super();
		this.cancelTemplate = cancelTemplate;
		this.confirmTemplate = confirmTemplate;
		this.verifyTemplate = verifyTemplate;
		this.reminderTemplate = reminderTemplate;
		this.updateTemplate = updateTemplate;
	}

	public TemplateConfig() {
		super();
	}
	
}
