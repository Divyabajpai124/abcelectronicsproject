package com.abcelectronicsmartservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "Engineer")
public class Engineer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer engineerId;
	private String engineerName;
	private String password;
	private String domain;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "complaint_id", referencedColumnName = "complaintId")
	@JsonIgnoreProperties
	private Complaint complaint;
	
	public Engineer() {
		super();
	}
	
	public Engineer(Integer engineerId, String password, String engineerName, String domain, List<Complaint> complaints) {
		super();
		this.engineerId = engineerId;
		this.password = password;
		this.engineerName = engineerName;
		this.domain = domain;
	}
	
	public Integer getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
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
		return "Engineer [engineerId=" + engineerId + ", engineerName=" + engineerName + ", password=" + password
				+ ", domain=" + domain + "]";
	}
		
}
