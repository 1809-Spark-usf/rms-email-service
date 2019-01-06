package com.revature.reminder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The Class ReminderCheckEmail.
 * Every minute between the time
 * of 8:00am-5:00pm it will check 
 * the database for any reservation
 * reminders that need to be sent.
 */
@Service
public class ReminderCheckEmail {
	public Integer i = 0;
	/**
	 * Send test.
	 */
	@Scheduled(cron = "0 0/1 8-17 ? * 1-6")
	public void sendTest() {
		
		System.out.println("lets se if it checks every minute "+ i++);;
	}
}
