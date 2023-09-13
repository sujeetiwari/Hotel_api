package com.hm.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class AdminDTO {

		private int id;
	
		@NotNull(message = "Name is required")
		@Size(max=20,message="Maximum 20 character is allowed")
		@Size(min=5,message ="Minimum 5 character is allowed")
		@NotBlank(message="Enter valid user name")
	    private String username;
	
		@NotNull(message="Email is required")
		@Size(max=30,message="Maximum 30 character is allowed")
		@Email(message="Invalid E-Mail")
		@NotBlank(message="Enter valid email")
		private String email;
		
		@NotNull(message="password is required")
		@Size(max=60,message="Maximum 60 character is allowed")
		@NotBlank(message = "Enter valid password")
	    private String password;
	
     	
	
}
