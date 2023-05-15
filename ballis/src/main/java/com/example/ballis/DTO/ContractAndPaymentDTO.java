package com.example.ballis.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractAndPaymentDTO {
	private ContractAddDTO contractDto;
	private PaymentAddDTO paymentDto;
}
