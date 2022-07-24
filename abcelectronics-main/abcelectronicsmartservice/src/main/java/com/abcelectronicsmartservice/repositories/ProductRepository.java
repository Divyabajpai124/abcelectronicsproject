package com.abcelectronicsmartservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcelectronicsmartservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("from Product p where p.categoryName=:categoryName")
	public List<Product> getProduct(@Param(value = "categoryName") String categoryName);
	
	@Query("from Product p where p.modelNumber=:modelNumber")
	public Product getModelNumber(@Param(value = "modelNumber") Integer modelNumber); 
	
	@Query("from Product p where p.modelNumber=:modelNumber")
	public Product getProduct(@Param(value = "modelNumber") Integer modelNumber);
	
}
