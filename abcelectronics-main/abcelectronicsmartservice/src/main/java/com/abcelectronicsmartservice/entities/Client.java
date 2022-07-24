package com.abcelectronicsmartservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId; 
	private String address;
	private Long phoneNumber;
	private String password;
	@Column(columnDefinition = "boolean default false")
	private boolean isLogin = false;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "complaint_id", referencedColumnName = "complaintId")
	@JsonIgnoreProperties
	private Complaint complaint;
	
	public Client() {
		super();
		
	}
	
	
	public Client(Integer clinetId, String password, String address, Long phoneNumber, List<Product> productOwned,
			List<Complaint> complaintList) {
		super();
		this.clientId = clinetId;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public Integer getClinetId() {
		return clientId;
	}
	public void setClinetId(Integer clinetId) {
		this.clientId = clinetId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JsonIgnore
	public boolean isLogin() {
		return isLogin;
	}
	@JsonIgnore
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	@JsonIgnore
	public Complaint getComplaint() {
		return complaint;
	}
	@JsonIgnore
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}


	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", address=" + address + ", phoneNumber=" + phoneNumber + ", password="
				+ password + "]";
	}
	
	
}
