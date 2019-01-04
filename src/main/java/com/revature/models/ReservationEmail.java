package com.revature.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The ReservationEmail Class
 * Used to transfer information needed
 * by the RMSEmailService in an object.
 *
 * @author Austin D. | 1811-Java-Nick 1/3/19
 */
public class ReservationEmail {
	
	/** The email of the user making the reservation. */
	private String email;
	
	/** The start time of the reservation. */
	private LocalDateTime startTime;
	
	/** The end time of the reservation. */
	private LocalDateTime endTime;
	
	/** The building name the reservation is taking place in. */
	private String buildingName;
	
	/** The resource name associated with the building. */
	private String resourceName;
	
	/**
	 * Instantiates a new reservation email.
	 */
	public ReservationEmail() {
		super();
	}
	
	/* 
	 * To string
	 */
	@Override
	public String toString() {
		return "ReservationEmail [email=" + email + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", buildingName=" + buildingName + ", resourceName=" + resourceName + "]";
	}
	
	/**
	 * Instantiates a new reservation email.
	 *
	 * @param email the email
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param buildingName the building name
	 * @param resourceName the resource name
	 */
	public ReservationEmail(String email, LocalDateTime startTime, LocalDateTime endTime, String buildingName,
			String resourceName) {
		super();
		this.email = email;
		this.startTime = startTime;
		this.endTime = endTime;
		this.buildingName = buildingName;
		this.resourceName = resourceName;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Gets the building name.
	 *
	 * @return the building name
	 */
	public String getBuildingName() {
		return buildingName;
	}
	
	/**
	 * Sets the building name.
	 *
	 * @param buildingName the new building name
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	/**
	 * Gets the resource name.
	 *
	 * @return the resource name
	 */
	public String getResourceName() {
		return resourceName;
	}
	
	/**
	 * Sets the resource name.
	 *
	 * @param resourceName the new resource name
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
