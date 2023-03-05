package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.CurrentAdminSession;

public interface AdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer>{
     public CurrentAdminSession findByUuid(String key);
     public CurrentAdminSession findByAdminId(Integer adminId);
     
}
