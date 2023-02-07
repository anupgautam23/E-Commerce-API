package com.masai.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDTO {

	
	@NotNull(message = "first name is mandatory")
	private String firstName;
	
	@NotNull(message = "last name is mandatory")
	private String lastName;
	
	@NotNull(message = "mobile number is mandatory")
	@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$"
			,message ="Provide a Valid Mobile Number !")
	private String mobile ; 
	
	@Email(message = "provide a valid email")
	@NotNull(message = "email is mandatory")
	private String email;
	
	@NotNull(message = "password is mandatory")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
	message = "Password must be of 8 characters and must include "
			+ " at least one upper case English letter, one lower case "
			+ "English letter, one number and one special character.")
	private String password;
	
}
