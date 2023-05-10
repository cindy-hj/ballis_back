package com.example.ballis;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ballis.model.Selling;
import com.example.ballis.repository.SellingRepository;


@SpringBootTest
public class SellingRepositoryTests {

	@Autowired
	private SellingRepository sellingRepository;
	
	@Test
	public void testGetNew() {
		Optional<Selling> result = sellingRepository.findById(1L);
		
		Selling selling = result.get();
		
		System.out.println(selling);
		System.out.println(selling.getProduct());	
	}
	
}
