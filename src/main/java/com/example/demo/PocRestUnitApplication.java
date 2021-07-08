package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PocRestUnitApplication implements CommandLineRunner {

	@Autowired
	private RecordsRepo repo;
	public static void main(String[] args) {
		SpringApplication.run(PocRestUnitApplication.class, args);
		
		
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	    
	   Records info=new Records();
	   info.setAid(1l);
	    info.setFname("Gaurav");
		info.setLname("Rana");
		info.setPincode("110085");
		info.setDOB("1998-05-22");
		info.setDateOfJoining("2021-06-24");
		
		Records info1=new Records();
		info.setAid(2l);
		info1.setFname("sachin");
		info1.setLname("Joshi");
		info1.setPincode("110089");
		info1.setDOB("1998-12-12");
		info1.setDateOfJoining("2021-05-12");
	   
	   this.repo.save(info);
	   this.repo.save(info1);

	}

}
