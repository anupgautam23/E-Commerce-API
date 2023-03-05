package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Cart;
import com.masai.Model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
  
	
	public Cart findByCartId(Integer cartId);
}
