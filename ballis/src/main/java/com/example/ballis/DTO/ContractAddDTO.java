package com.example.ballis.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ContractAddDTO {

	private Long productId; // fk
	private Long buyingId; // nullable fk 판매시
	private Long sellingId; // nullable fk 구매시
	private Long buyerNumber; // 구매시(나) 판매시(상대방)
	private Long sellerNumber; // 구매시(상대방) 판매시(나)
	private LocalDateTime contractDate;
	private Integer sellingStatus; // nullable 구매시(18-판매완료)?
	private Integer buyingStatus; // nullable 판매시(38-배송중)?
	private Integer price;
	private LocalDateTime registDate;
	
}
