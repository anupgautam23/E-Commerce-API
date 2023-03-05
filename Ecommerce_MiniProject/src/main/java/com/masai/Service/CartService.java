package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.CartDTO;
import com.masai.Model.Product;

@Service
public interface CartService {

	public String addToCart(CartDTO cartDTO) throws UserException, ProductNotFoundException, CartNotFoundException;
	public String removeFromCart(Integer cartId,Integer prodId) throws CartNotFoundException, ProductNotFoundException;
	public List<Product> viewAllProductByCartId(Integer cartId) throws CartNotFoundException;
}
