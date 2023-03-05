package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exception.CategoryNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Product;

@Service
public interface ProductService {

	public Product addProduct(Product product,String key,Integer categoryId) throws CategoryNotFoundException, UserException;
	public Product ViewProductById(Integer prodId)throws ProductNotFoundException;
	public List<Product> ViewProductByName(String  prodName)throws ProductNotFoundException;
	public List<Product> ViewAllProduct() throws ProductNotFoundException;
	public Product addQuntityOfProduct(Integer prodId,Integer qty) throws ProductNotFoundException;
	public Product removeQuntityOfProduct(Integer prodId,Integer qty)throws ProductNotFoundException;
	public List<Product> findProductByCategoryId(Integer catId) throws ClassNotFoundException, ProductNotFoundException;
}
