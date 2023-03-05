package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Cart;
import com.masai.Model.CartDTO;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Repository.CartRepository;
import com.masai.Repository.ProductRepository;
import com.masai.Repository.UserRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository prodRepo;
	

	@Override
	public String addToCart(CartDTO cartDTO) throws UserException, ProductNotFoundException, CartNotFoundException {
		 Optional<User> existUserOTP = userRepo.findById(cartDTO.getUserId());
//		 checking user existent
		 if(!existUserOTP.isPresent()) throw new UserException("user not exist");
		 User existUser = existUserOTP.get();
		 
		 //checking product whether present on product table or not 
		 Optional<Product> product1OPT = prodRepo.findById(cartDTO.getProductId());
		 
		 if(!product1OPT.isPresent()) throw new ProductNotFoundException("product not found");
			
		 Product product = product1OPT.get();	 
		
		 Cart existCart = cartRepo.findByUser(existUser);
		 String msg = "";
		 if(existCart!=null) { 
		
		    List<Product> existList = existCart.getProducts();
		    existList.add(product);
		    existCart.setProducts(existList);		    
		    existCart.setProductQty(existCart.getProductQty()+cartDTO.getProductQty());
		    existCart.setTotalPrice(existCart.getTotalPrice()+cartDTO.getProductQty()*product.getProdPrice());
		    existUser.setCart(existCart);
		    msg = "existing cart ";
		    product.setCart(existCart);
		    prodRepo.save(product);
		    cartRepo.save(existCart);

		 }else{
			 
			Cart newCart =  new Cart();
			List<Product> list  = new ArrayList<>();
			list.add(product);
			newCart.setProducts(list);
			newCart.setProductQty(cartDTO.getProductQty());
			newCart.setTotalPrice(cartDTO.getProductQty()*product.getProdPrice());
			newCart.setUser(existUser);
			product.setCart(existCart);
			prodRepo.save(product);
			cartRepo.save(newCart);
			msg= "new cart";
		 }
		return msg+" is updated";
	}

	@Override
	public List<Product> viewAllProductByCartId(Integer cartId) throws CartNotFoundException {
		
	 Optional<Cart> existCart	= cartRepo.findById(cartId);
	 
	 if(!existCart.isPresent()) throw new CartNotFoundException("No Cart Found!!!");
	 Cart cart = existCart.get();
	 List<Product> list =   cart.getProducts();
		return list;
	}

	
	
	
	
	@Override
	public String removeFromCart(Integer cartId,Integer prodId) throws CartNotFoundException, ProductNotFoundException {
	Optional<Cart> existCartOPT = 	cartRepo.findById(cartId);
	if(!existCartOPT.isPresent()) throw new CartNotFoundException("cart not found ");
	Cart existCart = existCartOPT.get();
	 //checking product whether present on product table or not 
	 Optional<Product> product1OPT = prodRepo.findById(prodId);
	 
	 if(!product1OPT.isPresent()) throw new ProductNotFoundException("product not exist");
	 Product product = product1OPT.get();
//	 product.setCart(null);
//	 prodRepo.save(product);
	 List<Product> prodList = existCart.getProducts();
	 prodList.removeIf(p->p.getProdId()==prodId);
	 
	 existCart.setProducts(prodList);
	 existCart.setProductQty(existCart.getProductQty()-1);
	 existCart.setTotalPrice(existCart.getTotalPrice()-product.getProdPrice());

	 cartRepo.save(existCart);
	 
	
		return "product removed successfully from cart id: "+cartId;
	}

}
