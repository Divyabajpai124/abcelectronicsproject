package com.abcelectronicsmartservice.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcelectronicsmartservice.entities.Complaint;


@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	
	@Query("from Complaint c where c.complaintId=:complaintId")
	public Complaint getComplaint(@Param(value = "complaintId") Integer complaintId);  
	
	@Query("select c.engineerId from Complaint c where c.complaintId=:complaintId")
	public Integer getEngineerId(@Param(value = "complaintId") Integer complaintId);
	
	@Query("select c.modelNumber from Complaint c where c.complaintId=:complaintId")
	public Integer getModelNumber(@Param(value = "complaintId") Integer complaintId);
	
	@Query("from Complaint c where c.modelNumber=:modelNumber")
	public List<Complaint> getComplaintList(@Param(value = "modelNumber") Integer modelNumber);
	
	@Query("from Complaint c where c.clientId=:clientId")
	public List<Complaint> getClientAllComplaints(@Param(value = "clientId")Integer clientId);
	
	@Query("from Complaint c where c.clientId=:clientId and c.status = 'Open'")
	public List<Complaint> getClientAllOpenComplaint(@Param(value = "clientId")Integer clientId);
	
	@Query("from Complaint c where c.engineerId=:engineerId and c.status = 'Open'")
	public List<Complaint> getOpenComplaint(@Param(value = "engineerId")Integer engineerId);
	
	@Query("from Complaint c where c.engineerId=:engineerId and c.status = 'Close'")
	public List<Complaint> getResolvedComplaint(@Param(value = "engineerId")Integer engineerId);
	
	@Query("from Complaint c where c.engineerId=:engineerId and c.status=:status")
	public List<Complaint> getComplaints(@Param(value = "engineerId")Integer engineerId, @Param(value = "status") String status);
	
}
