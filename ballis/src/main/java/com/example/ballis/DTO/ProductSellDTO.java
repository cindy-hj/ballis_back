package com.example.ballis.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSellDTO {

	private Long id;
	private String productEngName;
	private String productKorName;
	private String modelNumber;
	private Integer sizeMin;
	private Integer sizeMax;
	private Integer sizeUnit;
	
	private String imagePath;

	private Integer buyingStatus;
	private Integer buyProductSize;
	private Integer buyWishPrice;
	private Long buyerNumber;
	private Long buyingId;
	
//	private Integer inventoryDiv;
}
