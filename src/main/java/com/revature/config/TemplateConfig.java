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
	@Override
	public String toString() {
		return "TemplateConfig [cancelTemplate=" + cancelTemplate + ", confirmTemplate=" + confirmTemplate
				+ ", verifyTemplate=" + verifyTemplate + "]";
	}
	public TemplateConfig(String cancelTemplate, String confirmTemplate, String verifyTemplate) {
		super();
		this.cancelTemplate = cancelTemplate;
		this.confirmTemplate = confirmTemplate;
		this.verifyTemplate = verifyTemplate;
	}
	public TemplateConfig() {
		super();
	}
	
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return builder.modulesToInstall(new JavaTimeModule());
    }
	
}
