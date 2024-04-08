package com.raccon.simplecrud.model.person;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.model.congregation.Congregation;
import com.raccon.simplecrud.model.phone.PhoneNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Column
    private Date birthDate;

    @Column(unique = true)
    private String email;

    // este é um enum person_function
    @Column(name = "person_function")
    private PersonFunction personFunction;

    // este é um enum instrument
    @Column
    private PersonInstrument instrument;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference // Indica que esta é a parte gerenciada da associação
    private List<Address> address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonManagedReference // Indica que esta é a parte gerenciada da associação
    private List<PhoneNumber> phoneNumbers;

    @ManyToMany
    @JoinTable(name = "person_congregation", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "congregation_id"))
    // @JsonManagedReference // Indica que esta é a parte gerenciada da associação
    private List<Congregation> congregations;

}
