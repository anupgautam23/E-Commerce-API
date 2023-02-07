package com.masai.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.AdminDTO;
import com.masai.Model.CurrentAdminSession;
import com.masai.Repository.AdminRepository;
import com.masai.Repository.AdminSessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private AdminSessionRepository  adminSessionRepo;

	@Override
	public Admin registerAdmin(Admin admin) throws UserException {
		
	Admin existAdmin	=adminRepo.findByEmail(admin.getEmail());
	if(existAdmin!=null) {
		throw new UserException(" Admin is already registered  with email:"+admin.getEmail());
	}	
	
	 Admin mobAdmin =adminRepo.findByMobile(admin.getMobile());
	 if(mobAdmin!=null) throw new UserException(" This mobile number "+admin.getMobile()+" is already used");
	 
		return adminRepo.save(admin);
	}

	@Override
	public String loginAdmin(AdminDTO adminDTO) throws UserException {								
		
		
		    Admin existAdmin	=adminRepo.findByEmail(adminDTO.getEmail());
								
			if(existAdmin==null) throw new UserException("Please... signUp first!");								
								
			CurrentAdminSession existAdminSession	=adminSessionRepo.findByAdminId(existAdmin.getId());
							  
	   	    if(existAdminSession!=null)  throw new UserException("Admin Already logged in ");
							
    		if(!existAdmin.getPassword().equals(adminDTO.getPassword())) throw new UserException("password does not match ");
									
    		String key = RandomString.make(6);
    		
    	    CurrentAdminSession logedAdmin	=new CurrentAdminSession(existAdmin.getId(), key, LocalDateTime.now());
    	    
    	    adminSessionRepo.save(logedAdmin);
    	    
			return logedAdmin.toString();
								
			
	}

	@Override
	public String logOutAdmin(String key) throws UserException {
		 CurrentAdminSession loggedAdmin = adminSessionRepo.findByUuid(key);
		 if(loggedAdmin==null) throw new UserException("No Admin logged In ");
		 adminSessionRepo.delete(loggedAdmin);
		return "logout successfully...";
	}
	

}
