package com.apiAP.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Image;
import com.apiAP.app.repositories.IImageRepo;
import com.apiAP.exceptions.RequestException;

@Service
public class ImageService implements IImageService{

	@Autowired
	private IImageRepo imgRepo;
	
	
	@Override
	public void saveImg(Image img) {
		imgRepo.save(img);
		}


	@Override
	public Image findById(Long id) {
		return imgRepo.findById(id).orElseThrow(
				()-> new RequestException
				("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")
				);
	}


	@Override
	public void delteImg(Long id) {
		imgRepo.deleteById(id);
		
	}

}
