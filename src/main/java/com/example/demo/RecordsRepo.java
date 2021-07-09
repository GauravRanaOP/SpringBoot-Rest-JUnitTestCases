package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepo extends JpaRepository<Records, Long>{
	
	public List<Records> findByLname(String lname);
	
	public List<Records> findByFname(String fname);
	
	public List<Records> findByPincode(Long pincode);

		
}
