package com.example.ballis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ballis.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
