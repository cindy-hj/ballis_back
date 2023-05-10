package com.example.ballis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.DTO.BrandListDTO;
import com.example.ballis.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	public List<BrandListDTO> findAllBrandListDTOs() {
		return brandRepository.findAllBrandListDTO();
	}
}
