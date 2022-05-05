package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.TechProject;


@Repository
public interface ITechProjectRepo extends JpaRepository<TechProject, Long> {
	
	
}
