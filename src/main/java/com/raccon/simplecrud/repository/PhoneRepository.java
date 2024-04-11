package com.raccon.simplecrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.model.phone.PhoneNumber;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findAllByPerson(Person person);

    boolean existsByPhone(String phone);

    PhoneNumber findPhoneNumberById(Long id);

}
