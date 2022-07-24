package com.abcelectronicsmartservice.services;

import java.util.List;

import com.abcelectronicsmartservice.entities.Client;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.exceptions.InvalidClientIdException;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidCredentialsException;
import com.abcelectronicsmartservice.exceptions.InvalidDomainException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.exceptions.UserAlreadyExistException;

public interface ClientService {

	public String saveClient(Client c) throws UserAlreadyExistException, PleaseCheckDetailsException;
	
	public Client getClientByCLientId(Integer clientId)throws InvalidClientIdException ;	
	
	public Engineer getEngineerById(Integer engineerId) throws InvalidEngineerIdException;
	
	public List<Engineer> getEngineersByDomain(String domain) throws InvalidDomainException;
	
	public String changeStatusOfComplaint(Integer complaintId, String status) throws InvalidStatusException, InvalidComplaintIdException; 
	
	public String login(Integer clientId, String password) throws InvalidCredentialsException;
	
	public String signOut(Integer clientId) throws InvalidCredentialsException;

}
