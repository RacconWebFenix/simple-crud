package com.raccon.simplecrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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

    public Address createAddress(Address address) {
        Address newAddress = new Address();
        address.setStreet(address.getStreet());
        address.setCity(address.getCity());
        address.setState(address.getState());
        address.setPostalCode(address.getPostalCode());

        // Verifique se há uma pessoa associada antes de definir
        // a pessoa no endereço
        if (address.getPerson() != null) {
            Person person = personRepository.findById(address.getPerson().getId()).orElse(null);
            address.setPerson(person);
        }

        return addressRepository.save(newAddress);
    }

    public Address getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElse(null);
    }

    public Address updateAddress(Long id, Address address) {
        Optional<Address> existingAddressOptional = addressRepository.findById(id);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            // Set other fields accordingly
            return addressRepository.save(existingAddress);
        } else {
            return null; // Or throw AddressNotFoundException
        }
    }

    public boolean deleteAddress(Long id) {
        Optional<Address> existingAddressOptional = addressRepository.findById(id);
        if (existingAddressOptional.isPresent()) {
            addressRepository.delete(existingAddressOptional.get());
            return true;
        } else {
            return false; // Or throw AddressNotFoundException
        }
    }

}
