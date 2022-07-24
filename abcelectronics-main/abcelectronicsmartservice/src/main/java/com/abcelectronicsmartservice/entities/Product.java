package com.abcelectronicsmartservice.entities;

import java.time.LocalDate;

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
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer modelNumber;
	private String productName;
	private String categoryName;
	private LocalDate dateofPurchase;
	private Integer warrentyYears;
	private LocalDate warrantyDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "complaint_id", referencedColumnName = "complaintId")
	@JsonIgnoreProperties
	private Complaint complaint;
	

	public Product() {
		super();
	}
	
	public Product(Integer modelNumber, String productName, String categoryName, LocalDate dateofPurchase,
			Integer warrentyYears, LocalDate warrantyDate) {
		super();
		this.modelNumber = modelNumber;
		this.productName = productName;
		this.categoryName = categoryName;
		this.dateofPurchase = dateofPurchase;
		this.warrentyYears = warrentyYears;
		this.warrantyDate = warrantyDate;
	}

	public Integer getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(Integer modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LocalDate getDateofPurchase() {
		return dateofPurchase;
	}

	public void setDateofPurchase(LocalDate dateofPurchase) {
		this.dateofPurchase = dateofPurchase;
	}

	public Integer getWarrentyYears() {
		return warrentyYears;
	}

	public void setWarrentyYears(Integer warrentyYears) {
		this.warrentyYears = warrentyYears;
	}

	public LocalDate getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(LocalDate warrantyDate) {
		this.warrantyDate = warrantyDate;
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
		return "Product [modelNumber=" + modelNumber + ", productName=" + productName + ", categoryName="
				+ categoryName + ", dateofPurchase=" + dateofPurchase + ", warrentyYears=" + warrentyYears
				+ ", warrantyDate=" + warrantyDate + "]";
	} 
	
}
