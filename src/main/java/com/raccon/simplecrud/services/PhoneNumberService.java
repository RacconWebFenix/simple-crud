package com.raccon.simplecrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.model.phone.PhoneNumber;
import com.raccon.simplecrud.repository.PhoneRepository;
import com.raccon.simplecrud.repository.personRepository.PersonRepository;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    PersonRepository personRepository;

    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneRepository.findAll();
    }

    public PhoneNumber getPhoneNumberById(Long id) {
        Optional<PhoneNumber> phoneNumberOptional = phoneRepository.findById(id);
        return phoneNumberOptional.orElse(null);
    }

    public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber) {
        PhoneNumber newPhoneNumber = new PhoneNumber();
        newPhoneNumber.setPhone(phoneNumber.getPhone());

        // Verifique se há uma pessoa associada antes de definir
        // a pessoa no número de telefone
        if (phoneNumber.getPerson() != null && phoneNumber.getPerson().getId() != null) {
            Person person = personRepository.findById(phoneNumber.getPerson().getId()).orElse(null);
            newPhoneNumber.setPerson(person);
        }

        return phoneRepository.save(newPhoneNumber);
    }

    public PhoneNumber updatePhoneNumber(Long id, PhoneNumber phoneNumber) {
        Optional<PhoneNumber> existingPhoneNumberOptional = phoneRepository.findById(id);
        if (existingPhoneNumberOptional.isPresent()) {
            PhoneNumber existingPhoneNumber = existingPhoneNumberOptional.get();
            existingPhoneNumber.setPhone(phoneNumber.getPhone());
            // Set other fields accordingly
            return phoneRepository.save(existingPhoneNumber);
        } else {
            return null; // Or throw PhoneNumberNotFoundException
        }
    }

    public boolean deletePhoneNumber(Long id) {
        Optional<PhoneNumber> existingPhoneNumberOptional = phoneRepository.findById(id);
        if (existingPhoneNumberOptional.isPresent()) {
            phoneRepository.delete(existingPhoneNumberOptional.get());
            return true;
        } else {
            return false; // Or throw PhoneNumberNotFoundException
        }
    }
}
