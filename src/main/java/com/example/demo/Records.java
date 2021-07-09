package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name="Records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Records {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long aid;
	
	@NonNull
	private String fname;                                                    //Domain Layer
	
	@NonNull
	private String lname;

	@NonNull
	private Long pincode;
	
	@NonNull
	private String DOB;
	
	@NonNull
	private String DateOfJoining;
	
	

}