package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Cart;
import com.masai.Model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	public 	Cart findByUser(User user);
}
