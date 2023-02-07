package com.masai.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Model.Address;
import com.masai.Model.CurrentUserSession;
import com.masai.Model.User;
import com.masai.Model.UserDTO;
import com.masai.Repository.AddressRepository;
import com.masai.Repository.UserRepository;
import com.masai.Repository.UserSessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserSessionRepository userSession ; 
	
	@Autowired
	private AddressRepository addressRepo ; 
	
	public  Address saveAddress(Address address,Integer userId) {
		 Optional<User> opt =userRepo.findById(userId);
		 
		address.setUser(opt.get());
		
		return addressRepo.save(address);
		
	}

	@Override
	public User registerUser(User user) throws UserException {
		
	  User existUser	= userRepo.findByEmail(user.getEmail());
	  
	  if(existUser!=null) throw new UserException("User Already registered with email:"+user.getEmail());
	  
	  User mobUser =userRepo.findByMobile(user.getMobile());
		 if(mobUser!=null) throw new UserException(" This mobile number "+user.getMobile()+" is already used... Please register with different mobile number ");
		   

		 
		return  userRepo.save(user);
	}

	@Override
	public String loginUser(UserDTO userDTO) throws UserException {
		User existUser =  userRepo.findByEmail(userDTO.getEmail());
		if(existUser==null) throw new UserException("Please register first");
		CurrentUserSession existCurrentUser = userSession.findByUserId(existUser.getId());
		if(existCurrentUser!=null) throw new UserException("User is already logged in...");
		if(!existUser.getPassword().equals(userDTO.getPassword())) throw new UserException("password does not match ");
		String key = RandomString.make(6);
		CurrentUserSession users = new CurrentUserSession(existUser.getId(), key, LocalDateTime.now());	
		userSession.save(users);
		return users.toString();
	}

	@Override
	public List<User> getUserDetails() {
		List<User> listUser = userRepo.findAll();
		return listUser;
	}

	@Override
	public String Logout(String key) throws UserException {
	CurrentUserSession existUser	=userSession.findByUuid(key);
	if(existUser==null)throw new UserException("User is not logged in");
	userSession.delete(existUser);
		return "log Out successfully...";
	}

	

}
