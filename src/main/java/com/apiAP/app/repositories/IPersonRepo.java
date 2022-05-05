package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.Person;

@Repository
public interface IPersonRepo extends JpaRepository<Person, Long>{

}
