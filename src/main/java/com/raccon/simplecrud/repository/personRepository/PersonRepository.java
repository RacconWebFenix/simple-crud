package com.raccon.simplecrud.repository.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raccon.simplecrud.model.person.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
}