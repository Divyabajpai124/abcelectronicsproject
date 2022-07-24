package com.abcelectronicsmartservice.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidClientIdException;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.repositories.ClientRepository;
import com.abcelectronicsmartservice.repositories.ComplaintRepository;
import com.abcelectronicsmartservice.repositories.EngineerRepository;
import com.abcelectronicsmartservice.repositories.ProductRepository;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;
	@Autowired
	private EngineerRepository engineerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public String bookComplaint(Complaint complaint) throws InvalidComplaintIdException, PleaseCheckDetailsException {
		if (complaintRepository.existsById(complaint.getComplaintId())) {
			throw new InvalidComplaintIdException("Complaint already Exists");
		} else if (complaint.getModelNumber() == 0 || complaint.getComplaintName().equals("string")
				|| complaint.getClientId() == 0 || complaint.getEngineerId() == 0) {
			throw new PleaseCheckDetailsException("Please check entered details");
		} else {
			Complaint c = complaintRepository.save(complaint);
			return "Complaint booked Successfully" + c;
		}
	}

	@Override
	public String changeComplaintStatus(Integer complaintId, String status)
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
	public List<Complaint> getClientAllComplaints(Integer clientId) throws InvalidClientIdException {
		if (clientRepository.existsById(clientId)) {
			List<Complaint> complaint = complaintRepository.getClientAllComplaints(clientId);
			return complaint;
		}
		throw new InvalidClientIdException("Client Id is Invalid");
	}

	@Override
	public List<Complaint> getClientAllOpenComplaints(Integer clientId) throws InvalidClientIdException {
		if (clientRepository.existsById(clientId)) {
			List<Complaint> complaint = complaintRepository.getClientAllOpenComplaint(clientId);
			return complaint;
		}
		throw new InvalidClientIdException("Client Id is Invalid");
	}

	@Override
	public Engineer getEngineer(Integer complaintId) throws InvalidComplaintIdException {
		if (complaintRepository.existsById(complaintId)) {
			Integer engId = complaintRepository.getEngineerId(complaintId);
			Engineer engineer = engineerRepository.getEngineer(engId);
			return engineer;
		}
		throw new InvalidComplaintIdException("Complaint Id is invalid");
	}

	@Override
	public Product getProductByComplaint(Integer complaintId) throws InvalidComplaintIdException {
		if (complaintRepository.existsById(complaintId)) {
			Integer modelNo = complaintRepository.getModelNumber(complaintId);
			Product product = productRepository.getProduct(modelNo);
			return product;
		}
		throw new InvalidComplaintIdException("Complaint Id is invalid");
	}
}
