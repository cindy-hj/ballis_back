package com.example.ballis.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyingChartDTO {
	
	private Long id;
	private Integer productSize;
	private Integer wishPrice;
	private Integer cnt;
	
}
