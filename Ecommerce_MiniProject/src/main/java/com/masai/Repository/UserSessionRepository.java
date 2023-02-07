package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.CurrentAdminSession;
import com.masai.Model.CurrentUserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<CurrentUserSession, Integer>{
	
	public CurrentUserSession findByUuid(String uuid);
    
    public CurrentUserSession findByUserId(Integer userId);

}
