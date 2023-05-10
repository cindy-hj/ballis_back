package com.example.ballis.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.DTO.SellingChartDTO;
import com.example.ballis.model.Selling;
import com.example.ballis.repository.SellingRepository;

@Service
public class SellingService {
	@Autowired
	private SellingRepository sellingRepository;
	
	// 판매입찰 차트
	public List<SellingChartDTO> findSellingByProduct_Id(Long productid) {
		List<Selling> sellings = sellingRepository.findByProduct_Id(productid);
	    Map<String, SellingChartDTO> sellingMap = new HashMap<>();
	    
	    for (Selling selling : sellings) {
	        Integer productSize = selling.getProductSize();
	        Integer wishPrice = selling.getWishPrice();
	        String key = productSize + "-" + wishPrice;
	        
	        // productSize, wishPrice 값이 동일하면 cnt 1 증가 후 배열에 넣지 않음
	        if (sellingMap.containsKey(key)) {
	            SellingChartDTO sellingChartDTO = sellingMap.get(key);
	            sellingChartDTO.setCnt(sellingChartDTO.getCnt() + 1);
	        } else {
	            SellingChartDTO sellingChartDTO = new SellingChartDTO(selling.getId(), productSize, wishPrice, 1);
	            sellingMap.put(key, sellingChartDTO);
	        }
	    }
	    
	    // 오름차순 정렬
	    List<SellingChartDTO> sellingChartDTOList = new ArrayList<>(sellingMap.values());
	    sellingChartDTOList.sort(Comparator.comparing(SellingChartDTO::getWishPrice));
	    
	    return sellingChartDTOList;
	}
	
}
