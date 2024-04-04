package com.raccon.simplecrud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.raccon.simplecrud.dto.AddressDTO;
import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.repository.AddressRepository;
import com.raccon.simplecrud.repository.personRepository.PersonRepository;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address createAddress(Address addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());

        // Verifique se há uma pessoa associada antes de definir
        // a pessoa no endereço
        if (addressDTO.getPerson() != null) {
            Person person = personRepository.findById(addressDTO.getPerson().getId()).orElse(null);
            address.setPerson(person);
        }

        return addressRepository.save(address);
    }

}
