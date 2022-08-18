package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiAP.app.models.Image;

public interface IImageRepo extends JpaRepository<Image, Long> {

}
