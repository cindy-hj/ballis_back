package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.ballis.model.Buying;

public interface BuyingRepository extends JpaRepository<Buying, Long> {
	
	List<Buying> findByProduct_Id(@Param("productid") Long productid);
}
