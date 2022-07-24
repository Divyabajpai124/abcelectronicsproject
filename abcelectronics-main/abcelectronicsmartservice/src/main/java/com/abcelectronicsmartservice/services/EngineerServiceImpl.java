package com.abcelectronicsmartservice.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.repositories.ComplaintRepository;
import com.abcelectronicsmartservice.repositories.EngineerRepository;

@Service
@Transactional
public class EngineerServiceImpl implements EngineerService {

	@Autowired
	private ComplaintRepository complaintRepository;
	@Autowired
	private EngineerRepository engineerRepository;

	@Override
	public List<Complaint> getAllOpenComplaints(Integer engineerId) throws InvalidEngineerIdException {
		if (engineerRepository.existsById(engineerId)) {
			List<Complaint> complaint = complaintRepository.getOpenComplaint(engineerId);
			return complaint;
		}
		throw new InvalidEngineerIdException("No Engineer Exist by this Id");
	}

	@Override
	public List<Complaint> getResolvedComplaints(Integer engineerId) throws InvalidEngineerIdException {
		if (engineerRepository.existsById(engineerId)) {
			List<Complaint> complaint = complaintRepository.getResolvedComplaint(engineerId);
			return complaint;
		}
		throw new InvalidEngineerIdException("No Engineer Exist by this Id");
	}

	@Override
	public List<Complaint> getComplaints(Integer engineerId, String status)
			throws InvalidEngineerIdException, InvalidStatusException {
		if (engineerRepository.existsById(engineerId)) {
			if (status.equalsIgnoreCase("open") || status.equalsIgnoreCase("close")) {
				List<Complaint> complaint = complaintRepository.getComplaints(engineerId, status);
				return complaint;
			}
			throw new InvalidStatusException("Please enter valid complaint status");
		}
		throw new InvalidEngineerIdException("No Engineer Exist by this Id");
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
}
