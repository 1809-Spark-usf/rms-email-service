# rms-email-service

1. This service requires an AWS access key and secret key in order to use that AWS SES account. These are stored in environment variables named "REFORCE_AWS_KEY" and "REFORCE_AWS_SECRET".

2. While the AWS SES account is in Sandbox mode, you will not be able to send to emails unless they have been verified with that account. For this reason, we were using a personal AWS SES account so that we could verify the accounts without needing to contact the product owner.

3. The email sender is set in an environment variable as well. The name of this environment variable is "REFORCE_AWS_SENDER".

4. Template names have been stored within variables through the email configuration files on the config service and pulled in through a configuration class "TemplateConfig". To add more templates, it is possible to add more variables to the config files and configuration class, or to add them as environment variables.

5. Testing in this service currently requires the config service to be online (even if the email service itself does not need to be running). To avoid this, you would need to migrate the template variables out of the config service and into the email service directly or in environment variables. These endpoint tests do send emails, and need a verified email in order to run properly. 
