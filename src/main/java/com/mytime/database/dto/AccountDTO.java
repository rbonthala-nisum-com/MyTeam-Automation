package com.mytime.database.dto;

public class AccountDTO {
	private Object _id;
	private String accountId;
	private String accountName;
	private String accountProjectSequence;
	private String status;
	private String clientAddress;
	private String industryType;
	private String[] deliveryManagers;
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountProjectSequence() {
		return accountProjectSequence;
	}
	public void setAccountProjectSequence(String accountProjectSequence) {
		this.accountProjectSequence = accountProjectSequence;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String[] getDeliveryManagers() {
		return deliveryManagers;
	}
	public void setDeliveryManagers(String[] deliveryManagers) {
		this.deliveryManagers = deliveryManagers;
	}
}
