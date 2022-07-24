package com.abcelectronicsmartservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.abcelectronicsmartservice.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientservice;

	@PostMapping("/saveClient")
	public ResponseEntity<String> saveClient(@RequestBody Client client)
			throws UserAlreadyExistException, PleaseCheckDetailsException {
		return new ResponseEntity<>(clientservice.saveClient(client), HttpStatus.CREATED);

	}

	@GetMapping("/getClient/{clientId}")
	public ResponseEntity<Client> getClientByCLientId(@PathVariable(value = "clientId") Integer clientId)
			throws InvalidClientIdException {
		return new ResponseEntity<>(clientservice.getClientByCLientId(clientId), HttpStatus.OK);
	}

	@GetMapping("/getEngineer/{engineerId}")
	public ResponseEntity<Engineer> getEngineerById(@PathVariable(value = "engineerId") Integer engineerId)
			throws InvalidEngineerIdException {
		return new ResponseEntity<>(clientservice.getEngineerById(engineerId), HttpStatus.OK);
	}

	@GetMapping("/getEngineersByDomain/{domain}")
	public ResponseEntity<List<Engineer>> getEngineersByDomain(@PathVariable(value = "domain") String domain)
			throws InvalidDomainException {
		return new ResponseEntity<>(clientservice.getEngineersByDomain(domain), HttpStatus.OK);
	}

	@PostMapping("/changeComplaintStatus")
	public ResponseEntity<String> changeStatus(@RequestParam Integer complaintId, String Status)
			throws InvalidStatusException, InvalidComplaintIdException {
		return new ResponseEntity<>(clientservice.changeStatusOfComplaint(complaintId, Status), HttpStatus.OK);
	}

	@PostMapping("/clientLogin")
	public ResponseEntity<String> login(@RequestParam Integer clientId, @RequestParam String password)
			throws InvalidCredentialsException {
		return new ResponseEntity<>(clientservice.login(clientId, password), HttpStatus.OK);
	}

}
