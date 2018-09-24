package com.mytime.database.dto;

public class DomainDTO {

	Object _id;
	String domainId;
	String domainName;
	String accountId;
	String status;
	String deliveryManagers[];
	public Object get_id() {
		return _id;
	}
	public void set_id(Object _id) {
		this._id = _id;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getDeliveryManagers() {
		return deliveryManagers;
	}
	public void setDeliveryManagers(String[] deliveryManagers) {
		this.deliveryManagers = deliveryManagers;
	}
}
