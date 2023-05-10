package com.example.ballis.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.DTO.ContractAddDTO;
import com.example.ballis.DTO.ContractChartDTO;
import com.example.ballis.model.Contract;
import com.example.ballis.repository.ContractRepository;

import jakarta.transaction.Transactional;

@Service
public class ContractService {
	@Autowired
	private ContractRepository contractRepository;
	
	public List<ContractChartDTO> getContractChart(Long productid) {
		return contractRepository.getContractChart(productid);
	}
	
	// 거래 체결
//	@Transactional
//	private Long productId; // fk
//	private Long buyingId; // nullable fk 판매시
//	private Long sellingId; // nullable fk 구매시
//	private Long buyerNumber; // 구매시(나) 판매시(상대방)
//	private Long sellerNumber; // 구매시(상대방) 판매시(나)
//	private LocalDateTime contractDate;
//	private Integer sellingStatus; // 구매시(18-판매완료)?
//	private Integer buyingStatus; // 판매시(38-배송중)?
//	private Integer price;
//	private LocalDateTime registDate;
//	
//	public Contract save(ContractAddDTO contractDto) {
//		Contract contract = new Contract();
//		
//		
//	}
}
