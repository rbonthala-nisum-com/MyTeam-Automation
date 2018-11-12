package com.mytime.database.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ProjectDTO {

	private Object _id;
	private String projectId;
	private String projectName;
	private String domain;
	private String status;
	private String[] managerIds;
	private String accountId;
	private String domainId;
	@JsonDeserialize(using = DateHandler.class)
	private Date projectStartDate;
	@JsonDeserialize(using = DateHandler.class)
	private Date projectEndDate;
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
		
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(String[] managerIds) {
		this.managerIds = managerIds;
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
	public Date getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public Date getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public String[] getDeliveryLeadIds() {
		return deliveryLeadIds;
	}
	public void setDeliveryLeadIds(String[] deliveryLeadIds) {
		this.deliveryLeadIds = deliveryLeadIds;
	}
	private String[] deliveryLeadIds;
	
}
