package com.masai.Service;

import java.util.List;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.PaymentNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.OrderDetail;

public interface OrderService {
	
	public String  addOrder(Integer paymentId ) throws PaymentNotFoundException, UserException;
	
	public List<OrderDetail> getOrderDetail(Integer userID) throws UserException, CartNotFoundException;

}
