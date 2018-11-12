package com.mytime.database.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TeamMateDTO {

	private Object _id;
	private String employeeId;
	private String employeeName;
	private String emailId;
	private String role;
	private String shift;
	private String projectId;
	private String projectName;
	private String account;
	private String designation;
	private String billableStatus;
	private int mobileNumber;
	@JsonDeserialize(using = DateHandler.class)
	private Date startDate;
	@JsonDeserialize(using = DateHandler.class)
	private Date endDate;
	@JsonDeserialize(using = DateHandler.class)
	private Date newBillingStartDate;
	private String active;
	private String accountId;
	private String domainId;
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBillableStatus() {
		return billableStatus;
	}
	public void setBillableStatus(String billableStatus) {
		this.billableStatus = billableStatus;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getNewBillingStartDate() {
		return newBillingStartDate;
	}
	public void setNewBillingStartDate(Date newBillingStartDate) {
		this.newBillingStartDate = newBillingStartDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
}
