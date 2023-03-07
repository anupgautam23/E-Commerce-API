package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.PaymentNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.OrderDetail;
import com.masai.Model.Payment;
import com.masai.Service.OrderService;
import com.masai.Service.PaymentService;

@RestController
@RequestMapping("/Order")
public class OrderController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addPayment")
	public ResponseEntity<Payment> addPayment(@RequestParam Integer cartId,@RequestParam String payType) throws CartNotFoundException {
	 Payment savedPay	 = paymentService.addPayment(cartId, payType);
		return new ResponseEntity<Payment>(savedPay,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/saveOrder")
	public ResponseEntity<String> saveOrder(Integer payId) throws PaymentNotFoundException, UserException{
		String message  = orderService.addOrder(payId);
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<OrderDetail>> getAllOrdersByUser(Integer userid) throws UserException, CartNotFoundException{
	 List<OrderDetail> list	=  orderService.getOrderDetail(userid);
	 return new ResponseEntity<List<OrderDetail>>(list,HttpStatus.ACCEPTED); 
	}
	

}
