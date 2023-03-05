package com.masai.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Integer paymentId ;
	private List<Product> products;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address shippingAddress;
	
	private Integer totalOrderPrice ;
	private String  message ; 
	
 	
}

