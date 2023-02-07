package com.masai.Service;

import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.AdminDTO;
import com.masai.Model.User;

@Service
public interface AdminService {
	
	public Admin registerAdmin(Admin admin) throws UserException;
	public String loginAdmin(AdminDTO adminDTO) throws UserException;
	public String logOutAdmin(String key) throws UserException;
}
