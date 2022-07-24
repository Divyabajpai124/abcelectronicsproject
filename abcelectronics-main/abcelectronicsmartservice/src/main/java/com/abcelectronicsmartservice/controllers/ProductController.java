package com.abcelectronicsmartservice.controllers;

import java.time.LocalDate;
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
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidCategoryNameException;
import com.abcelectronicsmartservice.exceptions.InvalidModelNumberException;
import com.abcelectronicsmartservice.exceptions.InvalidProductIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product)
			throws PleaseCheckDetailsException, InvalidProductIdException {
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);

	}

	@DeleteMapping("/deleteProduct")
	public ResponseEntity<String> removeProduct(@RequestParam Integer modelNumber) throws InvalidModelNumberException {
		return new ResponseEntity<>(productService.removeProducts(modelNumber), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getProduct/{categoryName}")
	public ResponseEntity<List<Product>> getProduct(@PathVariable(value = "categoryName") String categoryName)
			throws InvalidCategoryNameException {
		return new ResponseEntity<>(productService.getProduct(categoryName), HttpStatus.OK);
	}

	@PutMapping("/updateWarrantyDate")
	public ResponseEntity<String> updateWarrantyDate(@RequestParam Integer modelNumber,
			@RequestBody LocalDate warrantyDate) throws InvalidModelNumberException {
		return new ResponseEntity<>(productService.updateProductWarranty(modelNumber, warrantyDate), HttpStatus.OK);
	}

	@GetMapping("/getProductComplaints/{categoryName}")
	public ResponseEntity<List<Complaint>> getEngineersByDomain(
			@PathVariable(value = "categoryName") String categoryName) throws InvalidCategoryNameException {
		return new ResponseEntity<>(productService.getProductComplaints(categoryName), HttpStatus.OK);
	}
}
