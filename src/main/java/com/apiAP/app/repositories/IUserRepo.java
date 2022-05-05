package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

}
