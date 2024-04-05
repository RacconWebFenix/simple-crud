package com.raccon.simplecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.services.AddressService;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.CreateAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.DeleteByIdAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.GetAllAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.GetByIdAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.PutAddressRequestBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Endereços", description = "Endpoints para operações de endereços")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetAllAddressRequestBody
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddress();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @CreateAddressRequestBody
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address addressDTO) {

        Address createdAddress = addressService.createAddress(addressDTO);
        if (createdAddress != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetByIdAddressRequestBody
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
        Address address = addressService.getAddressById(id);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutAddressRequestBody
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(id, address);
        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteByIdAddressRequestBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        boolean deleted = addressService.deleteAddress(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}