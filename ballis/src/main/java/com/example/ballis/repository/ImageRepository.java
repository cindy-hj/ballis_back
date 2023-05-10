package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ballis.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByTargetIdAndPageDiv(int productid, int i);

}
