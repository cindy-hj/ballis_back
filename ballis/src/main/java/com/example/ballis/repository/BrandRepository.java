package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ballis.DTO.BrandListDTO;
import com.example.ballis.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	@Query("SELECT new com.example.ballis.DTO.BrandListDTO(b.brandId, b.brandName) FROM Brand b")
	List<BrandListDTO> findAllBrandListDTO();
}
