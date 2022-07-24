package com.abcelectronicsmartservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.abcelectronicsmartservice.exceptions.InvalidDomainException;
import com.abcelectronicsmartservice.exceptions.InvalidEngineerIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.exceptions.UserAlreadyExistException;
import com.abcelectronicsmartservice.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminservice;

	@PostMapping("/addEngineer")
	public ResponseEntity<String> addEngineer(@RequestBody Engineer engineer)
			throws UserAlreadyExistException, PleaseCheckDetailsException {
		return new ResponseEntity<>(adminservice.addEngineer(engineer), HttpStatus.CREATED);

	}

	@DeleteMapping("/deleteEngineer")
	public ResponseEntity<String> removeEngineer(@RequestParam Integer engineerId) throws InvalidEngineerIdException {
		return new ResponseEntity<>(adminservice.removeEngineer(engineerId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateDomain")
	public ResponseEntity<String> updateDomain(@RequestParam Integer engineerId, String newDomain)
			throws InvalidDomainException, InvalidEngineerIdException {
		return new ResponseEntity<>(adminservice.changeDomain(engineerId, newDomain), HttpStatus.OK);
	}

	@GetMapping("/getComplaintsByProducts/{categoryName}")
	public ResponseEntity<List<Complaint>> getComplaintsByProducts(
			@PathVariable(value = "categoryName") String categoryName) {
		return new ResponseEntity<>(adminservice.getComplaintsByProducts(categoryName), HttpStatus.OK);
	}

	@GetMapping("/getComplaints/{status}/{categoryName}")
	public ResponseEntity<List<Complaint>> getComplaints(@PathVariable(value = "status") String status,
			@PathVariable(value = "categoryName") String categoryName) {
		return new ResponseEntity<>(adminservice.getComplaints(status, categoryName), HttpStatus.OK);
	}
}
