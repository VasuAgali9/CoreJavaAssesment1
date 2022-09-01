package com.itc.com.repository;
import com.itc.com.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product getProductBypname(String pname);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Product WHERE pname=:pname")
	Integer deleteProductBypname(String pname);
}
