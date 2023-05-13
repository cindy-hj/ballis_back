package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.example.ballis.DTO.SellingChartDTO;
import com.example.ballis.model.Selling;

public interface SellingRepository extends JpaRepository<Selling, Long> {
	
	List<Selling> findByProduct_IdOrderByRegistDateDesc(@Param("productid") Long productid);
	
	// 빠른 배송 상품 존재 하는지
	List<Selling> findByInventoryDivAndSellingStatusAndProductId(Integer inventoryDiv, Integer sellingStatus, Long productid);
}

