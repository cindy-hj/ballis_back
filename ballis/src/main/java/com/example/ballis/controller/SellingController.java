package com.example.ballis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.DTO.SellingChartDTO;
import com.example.ballis.service.SellingService;

@RestController
public class SellingController {
	@Autowired
	private SellingService sellingService;
	
	@GetMapping("/api/get/sell/chart")
	public List<SellingChartDTO> findSellingByProduct(@RequestParam Long productid) {
		return sellingService.findSellingByProduct_Id(productid);
	}
	


}