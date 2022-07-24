package com.abcelectronicsmartservice.services;

import java.util.List;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;

public interface EngineerService {

	public List<Complaint> getAllOpenComplaints(Integer engineerId) throws InvalidEngineerIdException;
	
	public List<Complaint> getResolvedComplaints(Integer engineerId) throws InvalidEngineerIdException;
	
	public List<Complaint> getComplaints(Integer engineerId, String status) throws InvalidEngineerIdException, InvalidStatusException;
	
	public String changeComplaintStatus(Integer complaintId, String status)throws InvalidStatusException, InvalidComplaintIdException; // returns updated Status;
}
