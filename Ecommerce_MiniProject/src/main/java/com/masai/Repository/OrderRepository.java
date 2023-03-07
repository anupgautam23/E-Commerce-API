package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.OrderDetail;
import com.masai.Model.User;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer> {

//	public List<OrderDetail> findByUser(User user);
}
