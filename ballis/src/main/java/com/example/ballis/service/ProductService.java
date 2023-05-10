package com.example.ballis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.DTO.ProductAllDTO;
import com.example.ballis.DTO.ProductNewDTO;
import com.example.ballis.DTO.ProductOneDTO;
import com.example.ballis.DTO.ProductPopDTO;
import com.example.ballis.DTO.ProductBuyDTO;
import com.example.ballis.DTO.ProductBuyMethodDTO;
import com.example.ballis.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductNewDTO> getProductNew() {
		return productRepository.getProductNew();
	}

	public List<ProductPopDTO> getProductPop() {
		return productRepository.getProductPop();
	}
	
	public List<ProductOneDTO> getProductOne(Long productid) {
		return productRepository.getProductOne(productid);	
	}

	public List<ProductAllDTO> getProductAll(Integer sort) {
		return productRepository.getProductAll(sort);	
	}

	public List<ProductBuyDTO> getProductSelection(Long productid) {
		return productRepository.getProductSelection(productid);	
	}
	
	// 빠른 배송 상품 중 각 사이즈별 최저가 데이터만 출력
	public List<ProductBuyDTO> getFastProduct(Long productid) {
		return productRepository.getFastProduct(productid);
	}
	
	// 일반 배송 상품 중 각 사이즈별 최저가 데이터만 출력
	public List<ProductBuyDTO> getNormalProduct(Long productid) {
		return productRepository.getNormalProduct(productid);
	}
	
	// 각 사이즈별 빠른 배송, 일반 배송 상품이 둘다 존재할때 -> 판매가격이 더 낮은 데이터 출력
	public List<ProductBuyDTO> getBothProduct(Long productid) {
		List<ProductBuyDTO> fastProducts = getFastProduct(productid);
		List<ProductBuyDTO> normalProducts = getNormalProduct(productid);
		List<ProductBuyDTO> result = new ArrayList<>();
	    for (ProductBuyDTO fastProduct : fastProducts) {
	        for(ProductBuyDTO normalProduct : normalProducts) {
	            if(fastProduct.getSellProductSize().equals(normalProduct.getSellProductSize())) {
	                result.add(fastProduct.getSellWishPrice() <= normalProduct.getSellWishPrice() ? fastProduct : normalProduct);
	                break;
	            }
	        }
	    }
		return result;
	}
	
	// 각 사이즈별 빠른 배송, 일반 배송 상품이 둘다 존재할때 -> 판매가격이 더 낮은 데이터 출력
	// 빠른배송, 일반배송 중 하나만 존재할때 -> 하나만 존재하는것 출력
	public List<ProductBuyDTO> getCheaperProduct(Long productid) {
		List<ProductBuyDTO> fastProducts = getFastProduct(productid);
		List<ProductBuyDTO> normalProducts = getNormalProduct(productid);
		List<ProductBuyDTO> result = new ArrayList<>();
	    for (ProductBuyDTO fastProduct : fastProducts) {
	        boolean isSizeMatched = false;
	        for(ProductBuyDTO normalProduct : normalProducts) {
	            if(fastProduct.getSellProductSize().equals(normalProduct.getSellProductSize())) {
	                result.add(fastProduct.getSellWishPrice() <= normalProduct.getSellWishPrice() ? fastProduct : normalProduct);
	                isSizeMatched = true;
	                break;
	            }
	        }
	        if (!isSizeMatched) {
	            result.add(fastProduct);
	        }
	    }
	    for (ProductBuyDTO normalProduct : normalProducts) {
	        boolean isSizeMatched = false;
	        for(ProductBuyDTO fastProduct : fastProducts) {
	            if(normalProduct.getSellProductSize().equals(fastProduct.getSellProductSize())) {
	                isSizeMatched = true;
	                break;
	            }
	        }
	        if (!isSizeMatched) {
	            result.add(normalProduct);
	        }
	    }
		return result;
	}
	

	public List<ProductBuyMethodDTO> getProductBySize(Long productid, Integer size) {
		return productRepository.getProductBySize(productid, size);
	}
}
