package com.masai.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodId;
	@NotNull(message = "name is mandatory")
	private String prodName;
	
	@NotNull(message = "quantity is mandatory")
	private Integer prodQty;
	
	@NotNull(message = "colour is mandatory")
	private String prodColour;
	
	@NotNull(message = "size is mandatory")
	private Integer prodSize;
	
	
	private String prodDetail;
	
	@NotNull(message = "price is mandatory")
	private Integer prodPrice;
	
	private String seller;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
}
