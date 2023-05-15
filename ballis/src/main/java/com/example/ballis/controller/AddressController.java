package com.example.ballis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.model.Address;
import com.example.ballis.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	// 주소 조회
	@GetMapping("/api/get/address")
	public ResponseEntity<List<Address>> getAddress(@RequestParam Long member) {
		try {
			List<Address> lists = addressService.getAddress(member);
			return new ResponseEntity<>(lists, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
