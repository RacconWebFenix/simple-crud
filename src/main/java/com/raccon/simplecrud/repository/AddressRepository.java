package com.raccon.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raccon.simplecrud.model.addresses.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
