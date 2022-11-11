package com.codewithabhishek.upload.Service;

import java.awt.Image;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithabhishek.upload.entity.ImageData;
import com.codewithabhishek.upload.repository.StorageRepository;
import com.codewithabhishek.upload.util.ImageUtils;

@Service
public class StrogeService {
    
	@Autowired
	private StorageRepository repository;
	
	public String uploadImage(MultipartFile file) throws IOException {
		
	ImageData imageData = repository.save(ImageData.builder()
		   		 .name(file.getOriginalFilename())
				 .type(file.getContentType())
				 .imageData(ImageUtils.compressImage(file.getBytes()))
				  .build());
		if (imageData != null) {
			return "File uploaded successfully :" +file.getOriginalFilename();
		
	}
		return null;
	
}
	
	
	public byte[] downloadImage(String fileName) {
	     Optional<ImageData> dbImageData  =	repository.findByName(fileName);
	    byte[]image = ImageUtils.decompressImage(dbImageData.get().getImageData());
	     return image;
	}
	
	
}