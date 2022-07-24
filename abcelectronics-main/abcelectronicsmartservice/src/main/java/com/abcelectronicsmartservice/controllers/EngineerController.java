package com.abcelectronicsmartservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.exceptions.InvalidComplaintIdException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.InvalidStatusException;
import com.abcelectronicsmartservice.services.EngineerService;

@RestController
public class EngineerController {

	@Autowired
	private EngineerService engineerService;

	@GetMapping("/getAllOpenComplaints/{engineerId}")
	public ResponseEntity<List<Complaint>> getOpenComplaints(@PathVariable(value = "engineerId") Integer engineerId)
			throws InvalidEngineerIdException {
		return new ResponseEntity<>(engineerService.getAllOpenComplaints(engineerId), HttpStatus.OK);
	}

	@GetMapping("/getResolvedComplaints/{engineerId}")
	public ResponseEntity<List<Complaint>> getResolvedComplaints(@PathVariable(value = "engineerId") Integer engineerId)
			throws InvalidEngineerIdException {
		return new ResponseEntity<>(engineerService.getResolvedComplaints(engineerId), HttpStatus.OK);
	}

	@GetMapping("/getComplaints/{engineerId}/{status}")
	public ResponseEntity<List<Complaint>> getComplaints(@PathVariable(value = "engineerId") Integer engineerId,
			@PathVariable(value = "status") String status) throws InvalidEngineerIdException, InvalidStatusException {
		return new ResponseEntity<>(engineerService.getComplaints(engineerId, status), HttpStatus.OK);
	}

	@PutMapping("/changeComplaintStatus")
	public ResponseEntity<String> changeComplaintStatus(@RequestParam Integer complaintId, String status)
			throws InvalidStatusException, InvalidComplaintIdException {
		return new ResponseEntity<>(engineerService.changeComplaintStatus(complaintId, status), HttpStatus.OK);
	}
}
