package com.revature.models;

/**
 * The Class TemplatedEmail.
 */
public class TemplatedEmail {

	/** The start time. */
	private String startTime;
	
	/** The end time. */
	private String endTime;
	
	/** The building name. */
	private String buildingName;
	
	/** The resource name. */
	private String resourceName;
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
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
	
	/* 
	 */
	@Override
	public String toString() {
		return "TemplatedEmail [startTime=" + startTime + ", endTime=" + endTime + ", buildingName=" + buildingName
				+ ", resourceName=" + resourceName + "]";
	}
	
	/**
	 * Instantiates a new templated email.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param buildingName the building name
	 * @param resourceName the resource name
	 */
	public TemplatedEmail(String startTime, String endTime, String buildingName, String resourceName) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.buildingName = buildingName;
		this.resourceName = resourceName;
	}
	
	/**
	 * Instantiates a new templated email.
	 */
	public TemplatedEmail() {
		super();
		
	}
	
	
	
}
