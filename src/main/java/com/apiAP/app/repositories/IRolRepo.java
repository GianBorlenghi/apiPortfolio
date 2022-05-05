package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.Rol;

@Repository
public interface IRolRepo extends JpaRepository<Rol, Long> {

	
}
