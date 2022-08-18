package com.apiAP.app.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.apiAP.app.models.Technology;

public interface ITechnologyService {
	
	public void saveTechnology(Technology tec);
	public void deleteTechnology(Long id);
	public List<Technology> getAllTech();

}
