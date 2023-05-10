package com.example.ballis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.DTO.ContractChartDTO;
import com.example.ballis.service.ContractService;

@RestController
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@GetMapping("/api/get/contract/chart")
	public ResponseEntity<List<ContractChartDTO>> getProductOne(@RequestParam Long productid) {
	    try {
	        List<ContractChartDTO> lists = contractService.getContractChart(productid);
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
