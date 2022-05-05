package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.Technology;

@Repository
public interface ITechnologyRepo extends JpaRepository<Technology, Long>{

}
