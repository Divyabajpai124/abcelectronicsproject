package com.abcelectronicsmartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcelectronicsmartservice.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("from Client c where c.clientId=:clientId and c.password=:password")
	public Client getCredentials(@Param(value = "clientId")Integer clientId, @Param(value = "password") String password);
	
	@Query("from Client c where c.clientId=:clientId")
	public Client signOut(@Param(value = "clientId")Integer clientId);

}
