package com.abcelectronicsmartservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcelectronicsmartservice.entities.Engineer;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Integer> {

	@Query("from Engineer e where e.domain=:domain")
	public List<Engineer> getEngineersByDomain(@Param(value = "domain") String domain);
	
	@Query("from Engineer e where e.engineerId=:engineerId")
	public Engineer getEngineer(@Param(value = "engineerId") Integer engineerId);
	
}
