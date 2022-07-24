package com.abcelectronicsmartservice.services;

import java.util.List;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidDomainException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.exceptions.UserAlreadyExistException;

public interface AdminService {

	public String addEngineer(Engineer engineer) throws UserAlreadyExistException, PleaseCheckDetailsException;

	public String removeEngineer(Integer engineerId) throws InvalidEngineerIdException;

	public String changeDomain(Integer engineerId, String newDomain) throws InvalidDomainException, InvalidEngineerIdException; 
	
	public List<Complaint> getComplaintsByProducts(String categoryName);
	
	public List<Complaint> getComplaints(String status,String categoryName);
	
	public Complaint replaceEngineerFromComplaint(Integer complaintId)throws InvalidComplaintIdException;
}
