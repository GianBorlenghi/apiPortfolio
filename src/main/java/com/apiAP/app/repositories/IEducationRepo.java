package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.Education;

@Repository
public interface IEducationRepo extends JpaRepository<Education, Long> {

}
