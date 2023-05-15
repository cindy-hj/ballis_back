package com.example.ballis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballis.DTO.PaymentAddDTO;
import com.example.ballis.model.Payment;
import com.example.ballis.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	// 결제 완료
//	@PostMapping("/api/add/payment")
//	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
//		try {
//			Payment paymentInfo = paymentService.save(payment);
//			return new ResponseEntity<>(paymentInfo, HttpStatus.OK);	
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PostMapping("/api/add/payment")
    public ResponseEntity<Payment> addPayment(@RequestBody PaymentAddDTO paymentDto) {
        try {
            Payment payment = paymentService.addPayment(paymentDto);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
