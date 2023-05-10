package com.example.ballis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.DTO.BuyingAddDTO;
import com.example.ballis.DTO.BuyingChartDTO;
import com.example.ballis.model.Buying;
import com.example.ballis.service.BuyingService;

@RestController
public class BuyingController {

	@Autowired
	private BuyingService buyingService;
	
	@GetMapping("/api/get/buy/chart")
	public List<BuyingChartDTO> findBuyingByProduct(@RequestParam Long productid){
		return buyingService.findBuyingByProduct_Id(productid);
	}
	
	@PostMapping("/api/post/buy")
	public ResponseEntity<Buying> addBuying(@RequestBody BuyingAddDTO buyingDto) {
		try {
			Buying buyingInfo = buyingService.save(buyingDto);
			return new ResponseEntity<Buying>(buyingInfo, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
