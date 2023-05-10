package com.example.ballis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ballis.DTO.ReviewDTO;
import com.example.ballis.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "SELECT "
			+ "new com.example.ballis.DTO.ReviewDTO" 
			+ "(r.id, r.content, r.name, i.imagePath, i.mainImageDiv, i.id, p.id) "
			+ "FROM Review r "
			+ "INNER JOIN Image i ON r.id = i.targetId "
			+ "INNER JOIN Product p ON r.product = p.id "
			+ "WHERE r.dataStatus = 1 AND i.pageDiv = 2",
			nativeQuery = false) 
	List<ReviewDTO> getReviewAll();

	List<Review> findById(int reviewid);
	
}
