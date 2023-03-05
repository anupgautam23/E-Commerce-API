package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
