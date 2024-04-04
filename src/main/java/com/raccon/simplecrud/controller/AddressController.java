package com.raccon.simplecrud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raccon.simplecrud.dto.AddressDTO;
import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.services.AddressService;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.CreateAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.GetAllAddressRequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Endereços", description = "Endpoints para operações de endereços")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetAllAddressRequestBody
    @GetMapping
    public ResponseEntity<List<Address>> getMethodName() {
        List<Address> addresses = addressService.getAllAddress();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @CreateAddressRequestBody
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address addressDTO) {

        Address createdAddress = addressService.createAddress(addressDTO);
        if (createdAddress != null) {
            System.out.println(createdAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}