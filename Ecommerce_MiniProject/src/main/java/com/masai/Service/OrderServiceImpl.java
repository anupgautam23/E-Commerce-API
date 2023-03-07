package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.PaymentNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Address;
import com.masai.Model.Cart;
import com.masai.Model.OrderDetail;
import com.masai.Model.Payment;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Repository.CartRepository;
import com.masai.Repository.OrderRepository;
import com.masai.Repository.PaymentRepository;
import com.masai.Repository.UserRepository;



@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository ; 

	@Override
	public String addOrder(Integer paymentId) throws PaymentNotFoundException, UserException {
		
		String message = "";
		
		Optional<Payment> paymentOpt =  paymentRepo.findById(paymentId);
		 if(paymentOpt.isEmpty()) throw new PaymentNotFoundException("No Payment found");
		
		 Payment paymentDone  = paymentOpt.get();
		 	Integer cartId = paymentDone.getCartId();			
		 Cart cart = cartRepository.findById(cartId).get()	;
		 User user =cart.getUser();
		 if(user==null) throw new UserException("user not found for this cart id"+cart.getCartId());
		 
		 List<Address> addressList = cart.getUser().getAddresses();
		 if(addressList.size()==0) throw new UserException("Please add the address first");
		 Address address = addressList.get(0);
		
		OrderDetail newOrder = new OrderDetail();
		
		newOrder.setPaymentId(paymentId);
		newOrder.setShippingAddress(address);
		newOrder.setMessage("Order success");
		newOrder.setTotalOrderPrice(cart.getTotalPrice());
		newOrder.setOrderuser(user);
		
		List<OrderDetail> orderDetailsList =new ArrayList<>();
		orderDetailsList.add(newOrder);
		user.setOrderDetails(orderDetailsList);
		userRepository.save(user);
		orderRepository.save(newOrder);
		message = "order Success";		
		
		return message;
	}

	@Override
	public List<OrderDetail> getOrderDetail(Integer userID) throws UserException, CartNotFoundException {
		
		Optional<User> existUser =  userRepository.findById(userID);
		if(existUser.isEmpty()) throw new UserException("No user found with this user id ");
		User user = existUser.get();
		
		
		List<OrderDetail> orderDetails = user.getOrderDetails();
		System.out.println(orderDetails.size());
		if(orderDetails.size()==0) throw new UserException("order not found for this user");
		
		return orderDetails;
	}

}












