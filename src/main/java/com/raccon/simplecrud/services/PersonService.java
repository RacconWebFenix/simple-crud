package com.raccon.simplecrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccon.simplecrud.dto.AddressDTO;
import com.raccon.simplecrud.dto.CongregationDTO;
import com.raccon.simplecrud.dto.PersonDTO;
import com.raccon.simplecrud.dto.PhoneDTO;
import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.model.congregation.Congregation;
import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.model.person.PersonFunction;
import com.raccon.simplecrud.model.person.PersonInstrument;
import com.raccon.simplecrud.model.phone.PhoneNumber;
import com.raccon.simplecrud.repository.AddressRepository;
import com.raccon.simplecrud.repository.CongregationRepository;
import com.raccon.simplecrud.repository.PersonRepository;
import com.raccon.simplecrud.repository.PhoneRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private CongregationRepository congregationRepository;
    private PhoneRepository phoneRepository;
    private AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, CongregationRepository congregationRepository,
            PhoneRepository phoneRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.congregationRepository = congregationRepository;
        this.phoneRepository = phoneRepository;
        this.addressRepository = addressRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person createPerson(Person person) {
        // Salvar congregações primeiro

        List<Congregation> savedCongregations = new ArrayList<>();
        for (Congregation congregation : person.getCongregations()) {
            savedCongregations.add(congregationRepository.save(congregation));
        }

        person.setCongregations(savedCongregations);

        Person savedPerson = personRepository.save(person);

        List<PhoneNumber> phoneNumbers = person.getPhoneNumbers();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumber.setPerson(savedPerson);
            phoneRepository.save(phoneNumber);
        }

        List<Address> savedAddresses = new ArrayList<>();
        person.getAddress().forEach(add -> {
            savedAddresses.add(addressRepository.save(add));
        });

        person.setAddress(savedAddresses);

        return savedPerson;
    }

    @Transactional
    public Person updatePerson(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa com id " + id + " não encontrada."));

        Person updatedPerson = convertToEntity(personDTO);

        // Atualiza os campos não nulos do updatedPersonDTO
        person.updateFields(updatedPerson);

        updatePhoneNumbers(person, updatedPerson.getPhoneNumbers());

        updateAddresses(person, updatedPerson.getAddress());

        personRepository.save(person);

        return person;
    }

    private Person convertToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setCpf(personDTO.getCpf());
        person.setBirthDate(personDTO.getBirthDate());
        person.setEmail(personDTO.getEmail());

        // Convert personFunction from String to Enum
        if (personDTO.getPersonFunction() != null) {
            PersonFunction personFunction = PersonFunction.valueOf(personDTO.getPersonFunction().toUpperCase());
            person.setPersonFunction(personFunction);
        }

        // Convert instrument from String to Enum
        if (personDTO.getInstrument() != null) {
            PersonInstrument instrument = PersonInstrument.valueOf(personDTO.getInstrument().toUpperCase());
            person.setInstrument(instrument);
        }

        // Convert PhoneDTO list to PhoneNumber list
        if (personDTO.getPhoneNumbers() != null) {
            List<PhoneNumber> phoneNumbers = new ArrayList<>();
            for (PhoneDTO phoneDTO : personDTO.getPhoneNumbers()) {
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setId(phoneDTO.getId());
                phoneNumber.setPhone(phoneDTO.getPhone());
                phoneNumber.setPerson(person);
                phoneNumbers.add(phoneNumber);
            }
            person.setPhoneNumbers(phoneNumbers);
        }

        // Convert PhoneDTO list to PhoneNumber list
        if (personDTO.getAddress() != null) {
            List<Address> addresses = new ArrayList<>();
            for (AddressDTO addressDTO : personDTO.getAddress()) {
                Address address = new Address();
                address.setId(addressDTO.getId());
                address.setCity(addressDTO.getCity());
                address.setPostalCode(addressDTO.getPostalCode());
                address.setState(addressDTO.getState());
                address.setStreet(addressDTO.getStreet());
                address.setPerson(person);
                addresses.add(address);
            }
            person.setAddress(addresses);
        }

        return person;
    }

    private void updatePhoneNumbers(Person person, List<PhoneNumber> updatedPhoneNumbers) {
        List<PhoneNumber> phoneCopy = new ArrayList<>(person.getPhoneNumbers());

        if (updatedPhoneNumbers != null) {
            updatedPhoneNumbers.forEach(updatedPhone -> {
                if (updatedPhone.getId() != null) {
                    PhoneNumber existingPhone = findExistingPhone(phoneCopy, updatedPhone.getId());
                    existingPhone.setPhone(updatedPhone.getPhone());
                } else if (updatedPhone.getPhone() != null) {
                    PhoneNumber newPhone = createNewPhone(updatedPhone.getPhone(), person);
                    phoneCopy.add(newPhone);
                }
            });
        }

        person.setPhoneNumbers(phoneCopy);
    }

    private PhoneNumber findExistingPhone(List<PhoneNumber> phoneList, Long phoneId) {
        return phoneList.stream()
                .filter(phone -> phone.getId() != null && phone.getId().equals(phoneId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
    }

    private PhoneNumber createNewPhone(String phoneValue, Person person) {
        PhoneNumber newPhone = new PhoneNumber();
        newPhone.setPhone(phoneValue);
        newPhone.setPerson(person);
        return newPhone;
    }

    private void updateAddresses(Person person, List<Address> updatedAddresses) {
        List<Address> addressesCopy = new ArrayList<>(person.getAddress());

        if (updatedAddresses != null) {
            updatedAddresses.forEach(updatedAddress -> {
                if (updatedAddress.getId() != null) {
                    Address existingAdress = findExistingAddress(addressesCopy, updatedAddress.getId());
                    existingAdress.setCity(updatedAddress.getCity());
                    existingAdress.setPostalCode(updatedAddress.getPostalCode());
                    existingAdress.setState(updatedAddress.getState());
                    existingAdress.setStreet(updatedAddress.getStreet());
                } else if (updatedAddress.getPostalCode() != null) {
                    Address newAddress = createNewAddress(updatedAddress, person);
                    addressesCopy.add(newAddress);
                }
            });
        }

        person.setAddress(addressesCopy);
    }

    private Address findExistingAddress(List<Address> addressList, Long addresId) {
        return addressList.stream()
                .filter(address -> address.getId() != null && address.getId().equals(addresId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    private Address createNewAddress(Address updatedAddress, Person person) {
        Address newAddress = new Address();
        newAddress.setStreet(updatedAddress.getStreet());
        newAddress.setCity(updatedAddress.getCity());
        newAddress.setState(updatedAddress.getState());
        newAddress.setPostalCode(updatedAddress.getPostalCode());
        newAddress.setPerson(person);
        return newAddress;
    }

}
