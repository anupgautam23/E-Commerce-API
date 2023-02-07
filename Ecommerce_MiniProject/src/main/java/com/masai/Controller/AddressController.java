package com.masai.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Model.Address;
import com.masai.Service.UserService;
import com.masai.Service.UserServiceImpl;

@RestController
@RequestMapping("/Address")
public class AddressController {
	
	@Autowired
	private UserServiceImpl userSer; 
	
	
	@PostMapping("/save")
	private ResponseEntity<Address> saveAddress(@Valid @RequestBody Address address,@RequestParam Integer userId){
	      
		Address address2  = userSer.saveAddress(address,userId);
		return new ResponseEntity<Address>(address2,HttpStatus.ACCEPTED);
	}

}
