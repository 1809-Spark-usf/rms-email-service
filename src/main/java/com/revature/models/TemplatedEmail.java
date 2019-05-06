package com.revature.models;

public class TemplatedEmail {

	private int id;	
	private String startTime;
	private String endTime;
	private String buildingName;
	private String resourceName;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Override
	public String toString() {
		return "TemplatedEmail [startTime=" + startTime + ", endTime=" + endTime + ", buildingName=" + buildingName
				+ ", resourceName=" + resourceName + "]";
	}
	public TemplatedEmail(String startTime, String endTime, String buildingName, String resourceName) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.buildingName = buildingName;
		this.resourceName = resourceName;
	}
	

	public TemplatedEmail() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
