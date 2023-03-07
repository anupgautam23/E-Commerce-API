package com.masai.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Integer paymentId ;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address shippingAddress;
	
	private Integer totalOrderPrice ;
	private String  message ; 
	
	@JsonIgnore
	@ManyToOne
	private User orderuser;
}


