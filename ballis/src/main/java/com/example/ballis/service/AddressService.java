package com.example.ballis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.model.Address;
import com.example.ballis.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> getAddress(Long member) {
		return addressRepository.findByMemberMemberNumber(member);
	}
	
}
