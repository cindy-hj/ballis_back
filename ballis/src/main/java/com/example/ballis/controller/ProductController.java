package com.example.ballis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.DTO.ProductAllDTO;
import com.example.ballis.DTO.ProductBuyDTO;
import com.example.ballis.DTO.ProductFilterDTO;
import com.example.ballis.DTO.ProductNewDTO;
import com.example.ballis.DTO.ProductOneDTO;
import com.example.ballis.DTO.ProductPopDTO;
import com.example.ballis.DTO.ProductSearchResponseDTO;
import com.example.ballis.DTO.ProductBuyListDTO;
import com.example.ballis.DTO.ProductBuyMethodDTO;
import com.example.ballis.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/get/product/new")
	public ResponseEntity<List<ProductNewDTO>> getProductNew() {
	    try {
	        List<ProductNewDTO> lists = productService.getProductNew();
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	    
	@GetMapping("/api/get/product/pop")
	public ResponseEntity<List<ProductPopDTO>> getProductPop() {
	    try {
	        List<ProductPopDTO> lists = productService.getProductPop();
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("/api/get/product/one")
	public ResponseEntity<List<ProductOneDTO>> getProductOne(@RequestParam Long productid) {
	    try {
	        List<ProductOneDTO> lists = productService.getProductOne(productid);
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
//	@GetMapping("/api/get/product/buy")
//	public ResponseEntity<List<ProductBuyDTO>> getProductSelection(@RequestParam Long productid) {
//	    try {
//	        List<ProductBuyDTO> lists = productService.getProductSelection(productid);
//	        return new ResponseEntity<>(lists, HttpStatus.OK);
//	    } catch (Exception e) {
//	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}
//	
	
	
	
	
	// 각 사이즈별 최저가 상품 1.빠른배송, 2.일반배송, 3.둘다 존재할경우 둘중 하나, 4.둘다 존재할 경우 둘중 하나& 둘중 하나만 존재할경우 그 하나
	@GetMapping("/api/get/product/buy/all")
	public ResponseEntity<ProductBuyListDTO> getProduct(@RequestParam Long productid) {
		ProductBuyListDTO productBuyListDTO = new ProductBuyListDTO();
		productBuyListDTO.setFast(productService.getFastProduct(productid));
		productBuyListDTO.setNormal(productService.getNormalProduct(productid));
		productBuyListDTO.setBoth(productService.getBothProduct(productid));
		productBuyListDTO.setCheaper(productService.getCheaperProduct(productid));
		
		return new ResponseEntity<>(productBuyListDTO, HttpStatus.OK);
	}
		
	
	@GetMapping("/api/get/product/buy/method")
	public ResponseEntity<List<ProductBuyMethodDTO>> getProductBySize(@RequestParam Long productid, @RequestParam Integer size) {
	    try {
	        List<ProductBuyMethodDTO> lists = productService.getProductBySize(productid, size);
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PostMapping("/api/get/product/all")
	public ProductSearchResponseDTO search(@RequestBody ProductFilterDTO filterdto, @RequestParam Integer sort) {
		
		List<ProductAllDTO> targetList = new ArrayList<>();

		// sort값에 따라 정렬
		targetList = productService.getProductAll(sort);

		// 필터링-카테고리
		if(!filterdto.getCategoryList().isEmpty()) {
			targetList = targetList.stream()
					.filter(product -> filterdto.getCategoryList().contains(product.getCategory()))
					.collect(Collectors.toList());			
		}
		// 필터링-성별
		if(!filterdto.getGenderList().isEmpty()) {
			targetList = targetList.stream()
					.filter(product -> filterdto.getGenderList().contains(product.getGender()))
					.collect(Collectors.toList());
		}
		// 필터링-브랜드
		if(!filterdto.getBrandIdList().isEmpty()) {
			targetList = targetList.stream()
					.filter(product -> filterdto.getBrandIdList().contains(product.getBrandId()))
					.collect(Collectors.toList());
		}
		// 필터링-보관상태
		if(!filterdto.getInventoryDivList().isEmpty()) {
			targetList = targetList.stream()
					.filter(product -> filterdto.getInventoryDivList().contains(product.getInventoryDiv()))
					.collect(Collectors.toList());
		}
		// 필터링-사이즈
		if(!filterdto.getSizeList().isEmpty()) {
			targetList = targetList.stream()
					.filter(product -> filterdto.getSizeList().contains(product.getSize()))
					.collect(Collectors.toList());
		}
		// 필터링-가격
		if (!filterdto.getWishPriceList().isEmpty()) {
		    List<Integer> wishPriceList = filterdto.getWishPriceList();
		    int minWishPrice = Collections.min(wishPriceList);
		    int maxWishPrice = Collections.max(wishPriceList);

		    targetList = targetList.stream()
		            .filter(product -> {
		                int wishPrice = product.getWishPrice();
		                return wishPrice >= minWishPrice && wishPrice <= maxWishPrice;
		            })
		            .collect(Collectors.toList());
		}
		
		
		ProductSearchResponseDTO resultDto = new ProductSearchResponseDTO();
		resultDto.setProductAllList(targetList);
		
		return resultDto;
	}
	
	
}
