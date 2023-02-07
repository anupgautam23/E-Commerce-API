package com.masai.Model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	
	@NotNull(message = "state is mandatory")
	private String state ;
	
	@NotNull(message = "city  is not null")
	private String city ;
	
	@NotNull(message = "house number is not null")
	private Integer houseNo;
	
	@NotNull(message = "pincode is not null ")
//	@Size(min = 6,max = 6,message = "pincode should be length of 6 ")
	private Integer pinCode;
	
	@JsonIgnore
	@ManyToOne
	private User user;

	
	
}
