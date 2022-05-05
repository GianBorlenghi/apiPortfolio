package com.apiAP.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Technology;
import com.apiAP.app.repositories.ITechnologyRepo;

@Service
public class TechnologyService implements ITechnologyService {

	@Autowired
	private ITechnologyRepo repoTec;
	@Override
	public void saveTechnology(Technology tec) {
		repoTec.save(tec);
		
	}

}
