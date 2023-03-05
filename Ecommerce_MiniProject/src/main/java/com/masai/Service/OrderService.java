package com.masai.Service;

import com.masai.Exception.PaymentNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Order;

public interface OrderService {
	
	public String  addOrder(Integer paymentId ) throws PaymentNotFoundException, UserException;

}
