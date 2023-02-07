package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByEmail(String email);
	public Admin findByMobile(String mobile);

}
