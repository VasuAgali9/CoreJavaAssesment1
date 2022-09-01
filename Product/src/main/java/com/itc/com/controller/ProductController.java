package com.itc.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itc.com.entity.Product;
import com.itc.com.service.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductController {
	@Autowired
	ProductService productservice;
	
	//HANDLERS
	//Add product details
	@PostMapping("/save")
	 public ResponseEntity<Product> saveProduct(@RequestBody Product addproduct){
		Product p=this.productservice.saveProduct(addproduct);
		return new ResponseEntity<Product>(p,HttpStatus.CREATED);
	}
	
	//Get All the product details(List of products)
	@GetMapping("product")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> p=this.productservice.getAllProducts();
			return new ResponseEntity<List<Product>>(p,HttpStatus.OK);
			
	}
	
	//Get specific product details by id
	@GetMapping("product/{pid}")
	public ResponseEntity<?> getProductById(@PathVariable int pid){
		Product p=this.productservice.getProductById(pid);
		if(p!=null) {
			return new ResponseEntity<Product>(p,HttpStatus.OK);
		}
		return new ResponseEntity<String>("NOT-FOUND", HttpStatus.NOT_FOUND);
	}
	
	//Get specific product details by Name
	@GetMapping("productname/{pname}")
	public ResponseEntity<?> getProductByName(@PathVariable String pname){
		Product p=this.productservice.getProductByName(pname);
		if(p!=null) {
			return new ResponseEntity<Product>(p,HttpStatus.OK);
		}else 
			return new ResponseEntity<String>("Not-FOUND", HttpStatus.NOT_FOUND);
	}
	
	//Update product details by Id
	@PutMapping("product")
	 public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Product p=(Product) this.productservice.updateProduct(product);
		if(p!=null) 
			return new ResponseEntity <Product>(p,HttpStatus.OK);
		else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	//Delete specific product by Id
	@DeleteMapping("product/{pid}")
	public ResponseEntity<?> deleteProductById(@PathVariable("pid") Integer pid)
	{
		this.productservice.deleteProductById(pid);
		return new ResponseEntity<String>("Deleted succssfully",HttpStatus.OK);
	}
	
	//Dlete specific product by Name
	@DeleteMapping("productname/{pname}")
	public ResponseEntity<?> deleteProductByName(@PathVariable String pname){
		boolean p=this.productservice.deleteProductByName(pname);
		if(p==true) {
			return new ResponseEntity<String>("Successfully-Deleted",HttpStatus.OK);
		}else 
			return new ResponseEntity<String>("Not-FOUND", HttpStatus.NOT_FOUND);
	}
	
}
