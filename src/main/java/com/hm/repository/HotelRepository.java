package com.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
