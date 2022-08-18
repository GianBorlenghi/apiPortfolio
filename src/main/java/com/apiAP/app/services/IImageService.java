package com.apiAP.app.services;

import java.util.Optional;

import com.apiAP.app.models.Image;

public interface IImageService {

	public void saveImg(Image img);
	public Image findById(Long id);
	public void delteImg(Long id);
}
