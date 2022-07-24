package com.abcelectronicsmartservice.services;

import java.time.LocalDate;
import java.util.List;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidCategoryNameException;
import com.abcelectronicsmartservice.exceptions.InvalidModelNumberException;
import com.abcelectronicsmartservice.exceptions.InvalidProductIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;

public interface ProductService {

	public String addProduct(Product product) throws PleaseCheckDetailsException, InvalidProductIdException;
	
	public String removeProducts(Integer modelNumber) throws InvalidModelNumberException;
	
	public List<Product> getProduct(String categoryName) throws InvalidCategoryNameException;
	
	public String updateProductWarranty(Integer modelNumber, LocalDate warrantyDate)throws InvalidModelNumberException;
	
	public List<Complaint> getProductComplaints(String categoryName) throws InvalidCategoryNameException;
	
}
