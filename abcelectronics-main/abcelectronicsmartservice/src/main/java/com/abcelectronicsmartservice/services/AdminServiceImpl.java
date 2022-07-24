package com.abcelectronicsmartservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidDomainException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.exceptions.UserAlreadyExistException;
import com.abcelectronicsmartservice.repositories.ComplaintRepository;
import com.abcelectronicsmartservice.repositories.EngineerRepository;
import com.abcelectronicsmartservice.repositories.ProductRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EngineerRepository engineerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ComplaintRepository complaintRepository;

	@Override
	public String addEngineer(Engineer engineer) throws UserAlreadyExistException, PleaseCheckDetailsException {
		if (engineerRepository.existsById(engineer.getEngineerId())) {
			throw new UserAlreadyExistException("User Already Exist with this ID");
		} else if (engineer == null || engineer.getEngineerName().equals("string") || engineer.getDomain().equals("string")
				|| engineer.getDomain().equals("string")) {
			throw new PleaseCheckDetailsException("Please check the entered details");
		} else {
			Engineer e = engineerRepository.save(engineer);
			return "Engineer created Successfully" + e;
		}
	}

	@Override
	public String removeEngineer(Integer engineerId) throws InvalidEngineerIdException {
		Optional<Engineer> engineer = engineerRepository.findById(engineerId);
		if (!engineer.isPresent())
			throw new InvalidEngineerIdException("No Engineer found with this id");
		else {
			engineerRepository.deleteById(engineerId);
			return "Engineer removed Successfully";
		}
	}

	@Override
	public String changeDomain(Integer engineerId, String newDomain)
			throws InvalidDomainException, InvalidEngineerIdException {
		if (Objects.equals(newDomain, ""))
			throw new InvalidDomainException("Please enter correct domain name");
		Optional<Engineer> engineer = engineerRepository.findById(engineerId);
		if (engineer.isPresent()) {
			engineer.get().setDomain(newDomain);
			engineerRepository.save(engineer.get());
			return "Domain Changed Successfully";
		} else
			throw new InvalidEngineerIdException("No Engineer found with this id");
	}

	@Override
	public List<Complaint> getComplaintsByProducts(String categoryName) {
		List<Complaint> complaints = new ArrayList<>();
		for (Complaint complaint : complaintRepository.findAll()) {
			Optional<Product> product = productRepository.findById(complaint.getModelNumber());
			if (product.isPresent() && Objects.equals(product.get().getCategoryName(), categoryName))
				complaints.add(complaint);
		}
		return complaints;
	}

	@Override
	public List<Complaint> getComplaints(String status, String categoryName) {
		List<Complaint> complaints = new ArrayList<>();
		for (Complaint complaint : getComplaintsByProducts(categoryName)) {
			if (Objects.equals(complaint.getStatus(), status)) {
				complaints.add(complaint);
			}
		}
		return complaints;
	}

	@Override
	public Complaint replaceEngineerFromComplaint(Integer complaintId) throws InvalidComplaintIdException {
		Optional<Complaint> complaint = complaintRepository.findById(complaintId);
		if (complaint.isPresent()) {
			complaint.get().setEngineerId(0);
			return complaint.get();
		}
		throw new InvalidComplaintIdException("Complaint Id is Invalid");
	}

}
