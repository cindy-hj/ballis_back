package com.example.ballis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballis.model.Image;
import com.example.ballis.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public List<Image> findByTargetIdAndPageDiv(int productid, int i) {
		return imageRepository.findByTargetIdAndPageDiv(productid,i);
	}
}
