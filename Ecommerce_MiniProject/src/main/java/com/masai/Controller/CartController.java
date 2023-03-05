package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CartNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.CartDTO;
import com.masai.Model.Product;
import com.masai.Service.CartService;
import com.masai.Service.ProductService;

@RestController
@RequestMapping("/Cart")
public class CartController {
	
//	private CategoryService catService;
	
	@Autowired
	private ProductService prodService ; 
	
	@Autowired
	private CartService  cartService ; 
	
	@GetMapping("/FindProductByCatId")
	private ResponseEntity<List<Product>> findProductByCatId(@RequestParam  Integer catId) throws ClassNotFoundException, ProductNotFoundException{
	  List<Product> prodList	=prodService.findProductByCategoryId(catId);
		return new ResponseEntity<List<Product>>(prodList,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/FindProductByProdId")
	private ResponseEntity<Product> findProductByProdId(@RequestParam  Integer prodId) throws ProductNotFoundException{
	  Product prodList	=prodService.ViewProductById(prodId);
		return new ResponseEntity<Product>(prodList,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/AddToCart")
	private ResponseEntity<String> addToCart(@RequestBody CartDTO cartDto) throws UserException, ProductNotFoundException, CartNotFoundException{
		String res =cartService.addToCart(cartDto);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/ViewByCartId")
	private ResponseEntity<List<Product>> viewProductByCartId(@RequestParam Integer id) throws CartNotFoundException{
		List<Product>  list= cartService.viewAllProductByCartId(id);
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/removeFromCart")
	private ResponseEntity<String> removeFromCart(@RequestParam Integer cartId, @RequestParam Integer prodid) throws UserException, ProductNotFoundException, CartNotFoundException{
		String res =cartService.removeFromCart(cartId, prodid);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
		
	}

}
