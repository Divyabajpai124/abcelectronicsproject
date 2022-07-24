package com.abcelectronicsmartservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Engineer;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidClientIdException;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.services.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@PostMapping("/addComplaint")
	public ResponseEntity<String> addComplaint(@RequestBody Complaint complaint)
			throws InvalidComplaintIdException, PleaseCheckDetailsException {
		return new ResponseEntity<>(complaintService.bookComplaint(complaint), HttpStatus.CREATED);
	}

	@PutMapping("/changeComplaintStatus")
	public ResponseEntity<String> changeComplaintStatus(@RequestParam Integer complaintId, String status)
			throws InvalidStatusException, InvalidComplaintIdException {
		return new ResponseEntity<>(complaintService.changeComplaintStatus(complaintId, status), HttpStatus.OK);
	}

	@GetMapping("/getEngineer/{complaintId}")
	public ResponseEntity<Engineer> getEngineerById(@PathVariable(value = "complaintId") Integer complaintId)
			throws InvalidComplaintIdException {
		return new ResponseEntity<>(complaintService.getEngineer(complaintId), HttpStatus.OK);
	}

	@GetMapping("/getProduct/{complaintId}")
	public ResponseEntity<Product> getProductByComplaint(@PathVariable(value = "complaintId") Integer complaintId)
			throws InvalidComplaintIdException {
		return new ResponseEntity<>(complaintService.getProductByComplaint(complaintId), HttpStatus.OK);
	}

	@GetMapping("/getClientAllComplaints/{clientId}")
	public ResponseEntity<List<Complaint>> getClientAllComplaints(@PathVariable(value = "clientId") Integer clientId)
			throws InvalidClientIdException {
		return new ResponseEntity<>(complaintService.getClientAllComplaints(clientId), HttpStatus.OK);
	}

	@GetMapping("/getClientAllOpenComplaints/{clientId}")
	public ResponseEntity<List<Complaint>> getClientAllOpenComplaints(
			@PathVariable(value = "clientId") Integer clientId) throws InvalidClientIdException {
		return new ResponseEntity<>(complaintService.getClientAllOpenComplaints(clientId), HttpStatus.OK);
	}
}
