package com.example.ballis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.example.ballis.DTO.ProductFilterDTO;
import com.example.ballis.DTO.ProductNewDTO;
import com.example.ballis.DTO.ProductOneDTO;
import com.example.ballis.DTO.ProductPopDTO;
import com.example.ballis.DTO.ProductSearchResponseDTO;
import com.example.ballis.DTO.ProductSellDTO;
import com.example.ballis.DTO.ProductBuyListDTO;
import com.example.ballis.DTO.ProductMethodDTO;
import com.example.ballis.service.ProductService;
import com.example.ballis.service.SellingService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private SellingService sellingService;
	

	// 메인 - 발매일 순 조회 & 빠른배송 여부 확인
	@GetMapping("/api/get/product/new")
	public ResponseEntity<Map<String, Object>> getProductNew() {
	    try {
	        Map<String, Object> result = new HashMap<>();
	        List<ProductNewDTO> lists = productService.getProductNew();
	        List<Map<String, Object>> storage = new ArrayList<>();
	        for (ProductNewDTO productNewDTO : lists) {
	            boolean hasStorageProduct = sellingService.hasStorageProduct(productNewDTO.getId());
	            Map<String, Object> storageMap = new HashMap<>();
	            storageMap.put("id", productNewDTO.getId());
	            storageMap.put("hasStorage", hasStorageProduct);
	            storage.add(storageMap);
	        }
	        result.put("lists", lists);
	        result.put("storage", storage);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// 메인 - 인기순 조회 && 빠른배송 여부 확인
	@GetMapping("/api/get/product/pop")
	public ResponseEntity<Map<String, Object>> getProductPop() {
	    try {
	    	Map<String, Object> result = new HashMap<>();
	        List<ProductPopDTO> lists = productService.getProductPop();
	        List<Map<String, Object>> storage = new ArrayList<>();
	        for (ProductPopDTO productPopDTO : lists) {
	            boolean hasStorageProduct = sellingService.hasStorageProduct(productPopDTO.getId());
	            Map<String, Object> storageMap = new HashMap<>();
	            storageMap.put("id", productPopDTO.getId());
	            storageMap.put("hasStorage", hasStorageProduct);
	            storage.add(storageMap);
	        }
	        result.put("lists", lists);
	        result.put("storage", storage);
	        return new ResponseEntity<>(result, HttpStatus.OK);
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
	
	
	
	
	// 구매-각 사이즈별 최저가 상품 1.빠른배송, 2.일반배송, 3.둘다 존재할경우 둘중 하나, 4.둘다 존재할 경우 둘중 하나& 둘중 하나만 존재할경우 그 하나
	@GetMapping("/api/get/product/buy")
	public ResponseEntity<ProductBuyListDTO> getProduct(@RequestParam Long productid) {
		ProductBuyListDTO productBuyListDTO = new ProductBuyListDTO();
		productBuyListDTO.setFast(productService.getFastProduct(productid));
		productBuyListDTO.setNormal(productService.getNormalProduct(productid));
		productBuyListDTO.setBoth(productService.getBothProduct(productid));
		productBuyListDTO.setCheaper(productService.getCheaperProduct(productid));
		
		return new ResponseEntity<>(productBuyListDTO, HttpStatus.OK);
	}
		
	// 구매입찰/즉시구매, 판매입찰/즉시판매 선택시  
	@GetMapping("/api/get/product/method")
	public ResponseEntity<List<ProductMethodDTO>> getProductBySize(@RequestParam Long productid, @RequestParam Integer size) {
	    try {
	        List<ProductMethodDTO> lists = productService.getProductBySize(productid, size);
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// 상품 전체 리스트
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
	
	// 판매-대표 정보
	@GetMapping("/api/get/product/sell")
	public ResponseEntity<List<ProductSellDTO>> getSellingProduct(@RequestParam Long productid) {
	    try {
	        List<ProductSellDTO> lists = productService.getSellingProduct(productid);
	        return new ResponseEntity<>(lists, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
}
