package com.masai.Model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDTO {
	
	@Email(message = "email should be valid")
	@NotNull(message = "email is mandatory")
	private String email; 
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
	message = "Password must be of 8 characters and must include "
			+ " at least one upper case English letter, one lower case "
			+ "English letter, one number and one special character.")
	private String password ; 
	

}
