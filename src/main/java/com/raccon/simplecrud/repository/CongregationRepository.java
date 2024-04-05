package com.raccon.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raccon.simplecrud.model.congregation.Congregation;

@Repository
public interface CongregationRepository extends JpaRepository<Congregation, Long> {

}
