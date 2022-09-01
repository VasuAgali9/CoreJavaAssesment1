package com.itc.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itc.com.entity.Product;
import com.itc.com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productrepository;
	
	public Product saveProduct(Product product) {
		Product p=this.productrepository.save(product);
		return p;
	}
	
	public Product getProductById(int id) {
		Optional<Product> op=this.productrepository.findById(id);
		if(op!=null) {
			return op.get();
		}
		return null;
	}
	public List<Product> getAllProducts(){
		return this.productrepository.findAll();
	}
	
	public Product updateProduct(Product product) {
		if(this.productrepository.existsById(product.getPid())) {
			return this.productrepository.save(product);	
		}else 
			return null;
	}
	
	public void deleteProductById(int id) {
		this.productrepository.deleteById(id);
	}
	
    public boolean deleteProductByName(String pname) {
    	this.productrepository.deleteProductBypname(pname);
    	return true;
    }
    
    public Product getProductByName(String pname) {
    	Product p=this.productrepository.getProductBypname(pname);
    	if(p != null) {
    		return p;
    	}else 
    	return null;
	}

	 
	
}
