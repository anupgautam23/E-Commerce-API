package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Category;
import com.masai.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Product findByProdId(Integer prodId);
	public List<Product> findByCategory(Category category);

}
