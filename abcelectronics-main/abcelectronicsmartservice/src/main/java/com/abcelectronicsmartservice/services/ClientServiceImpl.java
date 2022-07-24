package com.abcelectronicsmartservice.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcelectronicsmartservice.entities.Client;
import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.exceptions.InvalidClientIdException;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidCredentialsException;
import com.abcelectronicsmartservice.exceptions.InvalidDomainException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.exceptions.UserAlreadyExistException;
import com.abcelectronicsmartservice.repositories.ClientRepository;
import com.abcelectronicsmartservice.repositories.ComplaintRepository;
import com.abcelectronicsmartservice.repositories.EngineerRepository;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	EngineerRepository engineerRepository;
	@Autowired
	ComplaintRepository complaintRepository;

	@Override
	public String saveClient(Client client) throws UserAlreadyExistException, PleaseCheckDetailsException {
		if (clientRepository.existsById(client.getClinetId())) {
			throw new UserAlreadyExistException("User Already Exist with this ID");
		} else if (client.getAddress().equals("string") || (client.getPhoneNumber() == 0)
				|| (client.getPassword().equals("string"))) {
			throw new PleaseCheckDetailsException("Please check the entered details");
		} else {
			Client c = clientRepository.save(client);
			return "Client saved successfully" + c;
		}
	}

	@Override
	public Client getClientByCLientId(Integer clientId) throws InvalidClientIdException {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new InvalidClientIdException("Client Id is Invalid"));

	}

	@Override
	public Engineer getEngineerById(Integer engineerId) throws InvalidEngineerIdException {
		return engineerRepository.findById(engineerId)
				.orElseThrow(() -> new InvalidEngineerIdException("No Engineer found with this id"));

	}

	@Override
	public List<Engineer> getEngineersByDomain(String domain) throws InvalidDomainException {
		List<Engineer> engineerList = engineerRepository.getEngineersByDomain(domain);
		if (!engineerList.isEmpty()) {
			return engineerList;
		}
		throw new InvalidDomainException("Please enter correct domain name");
	}

	@Override
	public String changeStatusOfComplaint(Integer complaintId, String status)
			throws InvalidStatusException, InvalidComplaintIdException {
		if (complaintRepository.existsById(complaintId)) {
			if (status.equalsIgnoreCase("close")) {
				Complaint complaint = complaintRepository.getComplaint(complaintId);
				if (complaint.getStatus().equalsIgnoreCase("open")) {
					complaint.setStatus(status);
					complaintRepository.save(complaint);
					return "Complaint status changed to " + status;
				}
			} else {
				throw new InvalidStatusException("Complaint status is invalid");
			}
		}
		throw new InvalidComplaintIdException("Complaint Id is invalid");
	}

	@Override
	public String login(Integer clientId, String password) throws InvalidCredentialsException {
		Client client = clientRepository.getCredentials(clientId, password);
		if (client != null && client.getClinetId().equals(clientId) && client.getPassword().equals(password)) {
			client.setLogin(true);
			clientRepository.save(client);
			return "Successful login";
		} else {
			throw new InvalidCredentialsException(" Please check your login credentials");
		}
	}

	@Override
	public String signOut(Integer clientId) throws InvalidCredentialsException {
		Client client = clientRepository.signOut(clientId);
		if (client != null && client.getClinetId().equals(clientId)) {
			client.setLogin(false);
			clientRepository.save(client);
			return "Successful LogOut";
		} else {
			throw new InvalidCredentialsException("Client Id is Invalid");
		}
	}

}
