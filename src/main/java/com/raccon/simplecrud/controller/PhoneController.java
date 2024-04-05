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

import com.raccon.simplecrud.model.phone.PhoneNumber;
import com.raccon.simplecrud.services.PhoneNumberService;
import com.raccon.simplecrud.utils.SwaggerAnotations.PhoneAnnotations.CreatePhoneRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.PhoneAnnotations.DeleteByIdPhoneRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.PhoneAnnotations.GetAllPhoneRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.PhoneAnnotations.GetByIdPhoneRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.PhoneAnnotations.PutPhoneRequestBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/phones")
@Tag(name = "Telefones", description = "Endpoints para operações de telefones")
public class PhoneController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetAllPhoneRequestBody
    @GetMapping
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = phoneNumberService.getAllPhoneNumbers();
        return ResponseEntity.status(HttpStatus.OK).body(phoneNumbers);
    }

    @GetByIdPhoneRequestBody
    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumber> getPhoneNumberById(@PathVariable("id") Long id) {
        PhoneNumber phone = phoneNumberService.getPhoneNumberById(id);
        if (phone != null) {
            return ResponseEntity.ok(phone);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CreatePhoneRequestBody
    @PostMapping
    public ResponseEntity<PhoneNumber> createPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        PhoneNumber createdPhoneNumber = phoneNumberService.createPhoneNumber(phoneNumber);
        if (createdPhoneNumber != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPhoneNumber);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutPhoneRequestBody
    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumber> updatePhoneNumber(@PathVariable("id") Long id,
            @RequestBody PhoneNumber phoneNumber) {
        PhoneNumber updatedPhoneNumber = phoneNumberService.updatePhoneNumber(id, phoneNumber);
        if (updatedPhoneNumber != null) {
            return ResponseEntity.ok(updatedPhoneNumber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteByIdPhoneRequestBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable("id") Long id) {
        boolean deleted = phoneNumberService.deletePhoneNumber(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
