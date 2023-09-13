package com.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    


   
}
