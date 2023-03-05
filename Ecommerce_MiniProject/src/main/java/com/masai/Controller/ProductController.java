package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CategoryNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Product;
import com.masai.Service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	
	@PostMapping("/Add")
	private ResponseEntity<Product> addProduct(@RequestBody Product product,@RequestParam String key ,@RequestParam Integer catId ) throws CategoryNotFoundException, UserException{
		Product savedProduct = prodService.addProduct(product, key,catId);
		return new ResponseEntity<Product>(savedProduct,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/viewAllProduct")
	private ResponseEntity<List<Product>> viewAllProduct() throws ProductNotFoundException{
	List<Product> lists	=prodService.ViewAllProduct();
	return new ResponseEntity<List<Product>>(lists,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/AddQuantity")
	private ResponseEntity<Product> addProductQuantity(@RequestParam Integer ProdId,@RequestParam Integer qty) throws ProductNotFoundException{
		Product addedQty = prodService.addQuntityOfProduct(ProdId, qty);
		return new ResponseEntity<Product>(addedQty,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/RemoveQuantity")
	private ResponseEntity<Product> RemoveProductQuantity(@RequestParam Integer ProdId,@RequestParam Integer qty) throws ProductNotFoundException{
		Product removedQty = prodService.removeQuntityOfProduct(ProdId, qty);
		return new ResponseEntity<Product>(removedQty,HttpStatus.ACCEPTED);
	}

}

