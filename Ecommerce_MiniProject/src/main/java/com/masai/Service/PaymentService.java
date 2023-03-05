package com.masai.Service;

import com.masai.Exception.CartNotFoundException;
import com.masai.Model.Payment;

public interface PaymentService {
	
	public Payment addPayment(Integer cartId , String payType) throws CartNotFoundException;
	

}
