package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/rec")
public class RecordsController {

	@Autowired
	RecordsRepo repo;
	
	
	@GetMapping
	public Page<Records> getAllRecords(
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy
			) {
		return repo.findAll(
				PageRequest.of(page.orElse(0),
						5,
						Sort.Direction.ASC, sortBy.orElse("aid"))
				);
	}
	
//	@GetMapping
//	public List<Records> getAllRecords(){
//		return repo.findAll();
//	}

	
	@GetMapping(value="{aid}")
	public Records getRecordById(@PathVariable(value="aid") Long aid) {
		return repo.findById(aid).get();
	}
	
	@GetMapping("/firstname")
	public List<Records> findFname(@RequestParam String fname) {
		return repo.findByFname(fname);
	}
	
	@GetMapping("/lastname")
	public List<Records> findLname(@RequestParam String lname) {
		return repo.findByLname(lname);
	}
	
	@GetMapping("/pincode")
	public List<Records> findPincode(@RequestParam Long pincode) {
		return repo.findByPincode(pincode);
	}
	
	
	
	
	
	@PostMapping
	public Records addRecords(@RequestBody Records records) {
		return repo.save(records);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	class InvalidRequestException extends RuntimeException {
	    public InvalidRequestException(String s) {
	        super(s);
	    }
	}
	
	
	
	
	
	
	@PutMapping
	public Records updateRecord(@RequestBody Records records) throws NotFoundException {
	    if (records == null || records.getAid() == 0) {
	        throw new InvalidRequestException("Record or ID must not be null!");
	    }
	    Optional<Records> optionalRecord = repo.findById(records.getAid());
	    if (optionalRecord.isEmpty()) {
	        throw new NotFoundException("Records with ID " + records.getAid() + " does not exist.");
	    }
	    Records existingRecord = optionalRecord.get();

	    existingRecord.setFname(records.getFname());
	    existingRecord.setLname(records.getLname());
	    existingRecord.setPincode(records.getPincode());
	    existingRecord.setDOB(records.getDOB());
	    existingRecord.setDateOfJoining(records.getDateOfJoining());
	    
	    return repo.save(existingRecord);
	}
	
	
	
	
	
	@DeleteMapping(value = "{aid}")
	public void deleteRecordsById(@PathVariable(value = "aid") Long aid) throws NotFoundException {
	    if (repo.findById(aid).isEmpty()) {
	        throw new NotFoundException("Records with ID " + aid + " does not exist.");
	    }
	    repo.deleteById(aid);
	}
}
