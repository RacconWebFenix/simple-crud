package com.raccon.simplecrud.model.person;

import java.util.List;

import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.model.phone.PhoneNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String cpf;

    private String birthDate;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public Person(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

}
