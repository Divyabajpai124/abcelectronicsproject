package com.abcelectronicsmartservice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "complaint")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer complaintId;
	private Integer modelNumber; 
	private String complaintName;
	private String status = "Open"; 
	private Integer clientId; 
	private Integer engineerId;
	
	@OneToOne(mappedBy = "complaint", targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product;
	
	@OneToOne(mappedBy = "complaint", targetEntity = Client.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;
	
	@OneToOne(mappedBy = "complaint", targetEntity = Engineer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Engineer engineer;
	
	public Complaint() {
		super();
		
	}
	
	public Complaint(Product p, Client c ) {
		this.modelNumber = p.getModelNumber();
		this.clientId = c.getClinetId();
	}

	public Complaint(Integer complaintId, Integer modelNumber, String complaintName, String status, Integer clientId,
			Integer engineerId) {
		super();
		this.complaintId = complaintId;
		this.modelNumber = modelNumber;
		this.complaintName = complaintName;
		this.status = status;
		this.clientId = clientId;
		this.engineerId = engineerId;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public Integer getProductModelNumber() {
		return modelNumber;
	}

	public void setProductModelNumber(Integer productModelNumber) {
		this.modelNumber = productModelNumber;
	}

	public String getComplaintName() {
		return complaintName;
	}

	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}
	@JsonIgnore
	public String getStatus() {
		return status;
	}
	@JsonIgnore
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

    @JsonIgnore
	public Product getProduct() {
		return product;
	}
    @JsonIgnore
	public void setProduct(Product product) {
		this.product = product;
	}
	@JsonIgnore
	public Integer getModelNumber() {
		return modelNumber;
	}
	@JsonIgnore
	public void setModelNumber(Integer modelNumber) {
		this.modelNumber = modelNumber;
	}
	@JsonIgnore
	public Client getClient() {
		return client;
	}
	@JsonIgnore
	public void setClient(Client client) {
		this.client = client;
	}
	@JsonIgnore
	public Engineer getEngineer() {
		return engineer;
	}
	@JsonIgnore
	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", modelNumber=" + modelNumber
				+ ", complaintName=" + complaintName + ", status=" + status + ", clientId=" + clientId + ", engineerId="
				+ engineerId + "]";
	}
	
}
