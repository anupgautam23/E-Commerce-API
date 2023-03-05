package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.PaymentNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Address;
import com.masai.Model.Cart;
import com.masai.Model.Order;
import com.masai.Model.Payment;
import com.masai.Model.Product;
import com.masai.Repository.CartRepository;
import com.masai.Repository.OrderRepository;
import com.masai.Repository.PaymentRepository;



@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public String addOrder(Integer paymentId) throws PaymentNotFoundException, UserException {
		
		String message = "";
		try {		
		
		Optional<Payment> paymentOpt =  paymentRepo.findById(paymentId);
		 if(paymentOpt.isEmpty()) throw new PaymentNotFoundException("No Payment found");
		
		 Payment paymentDone  = paymentOpt.get();
		 	Integer cartId = paymentDone.getCartId();			
		 Cart cart = cartRepository.findById(cartId).get()	;
		 
		 Address address = cart.getUser().getAddresses().get(0);
		 if(address==null) throw new UserException("Please add the address first");
		 List<Product> product =  cart.getProducts();
		
		Order newOrder = new Order();
		newOrder.setProducts(product);
		newOrder.setPaymentId(paymentId);
		newOrder.setShippingAddress(null);
		newOrder.setShippingAddress(address);
		newOrder.setMessage("Order success");
		newOrder.setTotalOrderPrice(cart.getTotalPrice());
		
		orderRepository.save(newOrder);
		message = "order Success";		
		} catch (UserException ue) {
			throw new UserException("Order cancelled please try again !!!"); 
			
		}
		return message;
	}

}












