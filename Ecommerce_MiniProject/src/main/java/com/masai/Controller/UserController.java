package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Model.UserDTO;
import com.masai.Service.UserService;
import com.masai.Service.UserServiceImpl;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService  userService ; 
	
	
	
	@PostMapping("/signUp")
	private ResponseEntity<User > regiserUser(@Valid @RequestBody User user) throws UserException{
		
		User regUser =userService.registerUser(user);
		
		return new ResponseEntity<User>(regUser,HttpStatus.ACCEPTED);
		
	}
	

	@PostMapping("/logIn")
	private ResponseEntity<String>  loginUser(@Valid @RequestBody UserDTO userDTO) throws UserException{
		
		String regUser  =userService.loginUser(userDTO);
		return new ResponseEntity<String>(regUser,HttpStatus.ACCEPTED);		
		
	}
	
	@GetMapping("/GetUserDetails")
	private ResponseEntity<List<User>> getuserDetails(){
		List<User> list =userService.getUserDetails();
		return new ResponseEntity<List<User>>(list,HttpStatus.ACCEPTED);	
	}
	
	@DeleteMapping("/Logout")
	private ResponseEntity<String> logoutUser(@RequestParam String key) throws UserException{
	String reString =	userService.Logout(key);
	return new ResponseEntity<String>(reString,HttpStatus.ACCEPTED);	
	}
	
}
