package com.revature.awscredential;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;

/**
 * The Class AwsCredentials.
 * Creates connection to 
 * amazon SES console.
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 */
public class AwsCredentials {

/**
 * Creates the AWS credentials.
 * Using an AWS key and secret 
 * to access the console remotely
 *
 * @return the AWS credentials
 * @throws IOException Signals that an I/O exception has occurred.
 */
private AWSCredentials createAWSCredentials() throws IOException {
		
		AWSCredentials credentials = new BasicAWSCredentials(System.getenv("REFORCE_AWS_KEY"),System.getenv("REFORCE_AWS_SECRET"));
		return credentials;
		
		}

		/**
		 * Creates the simple email service.
		 *
		 * @return the amazon simple email service
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		@SuppressWarnings("deprecation")
		public AmazonSimpleEmailService createSimpleEmailService() throws IOException {
			
		return new AmazonSimpleEmailServiceClient(createAWSCredentials());
		
		}
}
