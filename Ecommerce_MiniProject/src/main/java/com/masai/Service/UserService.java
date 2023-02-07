package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Model.UserDTO;


public interface UserService {

	public User registerUser(User user) throws UserException;
	public String loginUser(UserDTO userDTO) throws UserException;
	public String Logout(String key) throws UserException ; 
	public List<User> getUserDetails();
}
