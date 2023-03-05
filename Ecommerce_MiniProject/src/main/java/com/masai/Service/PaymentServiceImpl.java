package com.masai.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartNotFoundException;
import com.masai.Model.Cart;
import com.masai.Model.Payment;
import com.masai.Model.User;
import com.masai.Repository.CartRepository;
import com.masai.Repository.PaymentRepository;




@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private CartRepository cartRepo;

	
	
//	Payment add
	@Override
	public Payment addPayment(Integer cartId, String payType) throws CartNotFoundException {
		
	 Optional<Cart> cartDto	= 	cartRepo.findById(cartId) ;
	 if(!cartDto.isPresent()) throw new CartNotFoundException("No Cart Found with this cart id ");
	 Cart existCart = cartDto.get();
	 
	 User existUser = existCart.getUser();
	
	 
		Payment newPayment = new Payment();
		newPayment.setCartId(cartId);
		newPayment.setDateAndTime(LocalDateTime.now());
		newPayment.setPayType(payType);
		newPayment.setTotalPrice(existCart.getTotalPrice());
	
	 Payment savedPayment = paymentRepo.save(newPayment);
	
	return savedPayment;
	}

}
