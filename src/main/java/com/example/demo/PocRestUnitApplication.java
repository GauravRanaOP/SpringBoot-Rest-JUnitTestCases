package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PocRestUnitApplication implements CommandLineRunner  {

	@Autowired
	private RecordsRepo repo;
	public static void main(String[] args) {
		SpringApplication.run(PocRestUnitApplication.class, args);
		
		
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Records info=new Records();
		    info.setFname("Gaurav");
			info.setLname("Rana");
			info.setPincode("110085");
			info.setDOB("1998-05-22");
			info.setDateOfJoining("2021-06-24");
			
			Records info1=new Records();
			info1.setFname("Sachin");
			info1.setLname("Joshi");
			info1.setPincode("110089");
			info1.setDOB("1998-12-12");
			info1.setDateOfJoining("2021-05-12");
			
			Records info2=new Records();
			info2.setFname("Sahil");
			info2.setLname("Rawat");
			info2.setPincode("110509");
			info2.setDOB("1996-11-02");
			info2.setDateOfJoining("2020-11-17");
			
			Records info3=new Records();
			info3.setFname("Vaibhav");
			info3.setLname("Singh");
			info3.setPincode("122509");
			info3.setDOB("1996-05-14");
			info3.setDateOfJoining("2020-11-16");
		   
		   
		   this.repo.save(info);
		   this.repo.save(info1);
		   this.repo.save(info2);
		   this.repo.save(info3);

	}

}
