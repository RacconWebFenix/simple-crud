package com.raccon.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raccon.simplecrud.model.phone.PhoneNumber;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneNumber, Long> {

}
