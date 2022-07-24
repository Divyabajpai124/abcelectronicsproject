package com.abcelectronicsmartservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcelectronicsmartservice.entities.Complaint;
import com.abcelectronicsmartservice.entities.Product;
import com.abcelectronicsmartservice.exceptions.InvalidCategoryNameException;
import com.abcelectronicsmartservice.exceptions.InvalidModelNumberException;
import com.abcelectronicsmartservice.exceptions.InvalidProductIdException;
import com.abcelectronicsmartservice.exceptions.PleaseCheckDetailsException;
import com.abcelectronicsmartservice.repositories.ComplaintRepository;
import com.abcelectronicsmartservice.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ComplaintRepository complaintRepository;

	@Override
	public String addProduct(Product product) throws PleaseCheckDetailsException, InvalidProductIdException {
		if (productRepository.existsById(product.getModelNumber())) {
			throw new InvalidProductIdException("Product Id is Invalid");
		} else if (product.getProductName().equals("string") || product.getCategoryName().equals("string")) {
			throw new PleaseCheckDetailsException("Please check the entered details");
		}
		Product p = productRepository.save(product);
		return "Product created Successfully" + p;
	}

	@Override
	public String removeProducts(Integer modelNumber) throws InvalidModelNumberException {
		Optional<Product> product = productRepository.findById(modelNumber);
		if (!product.isPresent())
			throw new InvalidModelNumberException("Model number is Invalid");
		else {
			productRepository.deleteById(modelNumber);
			return "Product removed Successfully";
		}
	}

	@Override
	public List<Product> getProduct(String categoryName) throws InvalidCategoryNameException {
		List<Product> productList = productRepository.getProduct(categoryName);
		if (!productList.isEmpty()) {
			return productList;
		}
		throw new InvalidCategoryNameException("Category Name is Invalid");
	}

	@Override
	public String updateProductWarranty(Integer modelNumber, LocalDate warrantyDate)
			throws InvalidModelNumberException {
		Product product = productRepository.getModelNumber(modelNumber);
		if (product.getWarrantyDate() != null && product.getModelNumber().equals(modelNumber)) {
			product.setWarrantyDate(warrantyDate);
			productRepository.save(product);
			return "Product warranty date changed to " + warrantyDate;
		}
		throw new InvalidModelNumberException("Model number is Invalid");
	}

	@Override
	public List<Complaint> getProductComplaints(String categoryName) throws InvalidCategoryNameException {
		List<Complaint> complaints = new ArrayList<>();
		for (Complaint complaint : complaintRepository.findAll()) {
			Optional<Product> product = productRepository.findById(complaint.getModelNumber());
			if (product.isPresent() && Objects.equals(product.get().getCategoryName(), categoryName))
				complaints.add(complaint);
		}
		return complaints;
	}

}
