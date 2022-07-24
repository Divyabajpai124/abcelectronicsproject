package com.abcelectronicsmartservice.services;

import java.util.List;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidClientIdException;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;

public interface ComplaintService {

	public String bookComplaint(Complaint complaint)throws InvalidComplaintIdException, PleaseCheckDetailsException;
	
	public String changeComplaintStatus(Integer complaintId, String status) throws InvalidStatusException, InvalidComplaintIdException;
	
	public List<Complaint> getClientAllComplaints(Integer clientId) throws InvalidClientIdException;
	
	public List<Complaint> getClientAllOpenComplaints(Integer clientId) throws InvalidClientIdException;
	
	public Engineer getEngineer(Integer complaintId)throws InvalidComplaintIdException;
	
	public Product getProductByComplaint(Integer complaintId)throws InvalidComplaintIdException;
	
}
