package com.raccon.simplecrud.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccon.simplecrud.model.congregation.Congregation;
import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.model.phone.PhoneNumber;
import com.raccon.simplecrud.repository.CongregationRepository;
import com.raccon.simplecrud.repository.PersonRepository;
import com.raccon.simplecrud.repository.PhoneRepository;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final CongregationRepository congregationRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, CongregationRepository congregationRepository,
            PhoneRepository phoneRepository) {
        this.personRepository = personRepository;
        this.congregationRepository = congregationRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        // Salvar congregações primeiro

        List<Congregation> savedCongregations = new ArrayList<>();
        for (Congregation congregation : person.getCongregations()) {
            savedCongregations.add(congregationRepository.save(congregation));
        }
        person.setCongregations(savedCongregations);

        Person savedPerson = personRepository.save(person);

        // Associa o ID da pessoa aos números de telefone
        List<PhoneNumber> phoneNumbers = person.getPhoneNumbers();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumber.setPerson(savedPerson);
            phoneRepository.save(phoneNumber);
        }

        return savedPerson;
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        if (personRepository.existsById(id)) {
            updatedPerson.setId(id);
            return personRepository.save(updatedPerson);
        } else {
            throw new IllegalArgumentException("Person with id " + id + " not found.");
        }
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
