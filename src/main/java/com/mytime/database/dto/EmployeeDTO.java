package com.mytime.database.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class EmployeeDTO {

	private Object _id;
	private String employeeId;
	private String employeeName;
	private String emailId;
	private String role;
	private String designation;
	private String shift;
	private String baseTechnology;
	private String domain;
	private String empLocation;
	private String technologyKnown;
	private String mobileNumber;
	private String alternateMobileNumber;
	private String personalEmailId;
	private String functionalGroup;
	private String empStatus;
	private String employmentType;
	@JsonDeserialize(using = DateHandler.class)
	private Date dateOfJoining;
	private String gender;
	@JsonDeserialize(using = DateHandler.class)
	private Date createdOn;
	@JsonDeserialize(using = DateHandler.class)
	private Date dateOfBirth;
	private String hasPassport;
	@JsonDeserialize(using = DateHandler.class)
	private Date passportExpiryDate;
	private String  hasBI;
	@JsonDeserialize(using = DateHandler.class)
	private Date bIExpiryDate;
	@JsonDeserialize(using = DateHandler.class)
	private Date lastModifiedOn;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getBaseTechnology() {
		return baseTechnology;
	}
	public void setBaseTechnology(String baseTechnology) {
		this.baseTechnology = baseTechnology;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEmpLocation() {
		return empLocation;
	}
	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}
	public String getTechnologyKnown() {
		return technologyKnown;
	}
	public void setTechnologyKnown(String technologyKnown) {
		this.technologyKnown = technologyKnown;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}
	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}
	public String getPersonalEmailId() {
		return personalEmailId;
	}
	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}
	public String getFunctionalGroup() {
		return functionalGroup;
	}
	public void setFunctionalGroup(String functionalGroup) {
		this.functionalGroup = functionalGroup;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHasPassport() {
		return hasPassport;
	}
	public void setHasPassport(String hasPassport) {
		this.hasPassport = hasPassport;
	}
	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}
	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}
	public String getHasBI() {
		return hasBI;
	}
	public void setHasBI(String hasBI) {
		this.hasBI = hasBI;
	}
	public Date getbIExpiryDate() {
		return bIExpiryDate;
	}
	public void setbIExpiryDate(Date bIExpiryDate) {
		this.bIExpiryDate = bIExpiryDate;
	}
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

}
