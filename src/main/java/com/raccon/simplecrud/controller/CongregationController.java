package com.raccon.simplecrud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raccon.simplecrud.dto.AddressDTO;
import com.raccon.simplecrud.dto.CongregationDTO;
import com.raccon.simplecrud.model.addresses.Address;
import com.raccon.simplecrud.model.congregation.Congregation;
import com.raccon.simplecrud.model.person.Person;
import com.raccon.simplecrud.repository.AddressRepository;
import com.raccon.simplecrud.services.AddressService;
import com.raccon.simplecrud.services.CongregationService;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.DeleteByIdAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.AddressAnnotations.GetByIdAddressRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.CongregationAnnotation.CreateCongregationRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.CongregationAnnotation.DeleteCongregationRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.CongregationAnnotation.GetAllCongregationRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.CongregationAnnotation.GetByIdCongregationRequestBody;
import com.raccon.simplecrud.utils.SwaggerAnotations.CongregationAnnotation.PutCongregationRequestBody;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/congregations")
@Tag(name = "Congregações", description = "Endpoints para operações de Congregações")
public class CongregationController {

    @Autowired
    private CongregationService congregationService;

    @Autowired
    private AddressService addressService;

    @GetAllCongregationRequestBody
    @GetMapping
    public ResponseEntity<List<Congregation>> getAllCongregations() {
        List<Congregation> congregations = congregationService.getAllCongregations();
        return ResponseEntity.status(HttpStatus.OK).body(congregations);
    }

    @CreateCongregationRequestBody
    @PostMapping("/congregations")
    public ResponseEntity<?> createCongregation(@RequestBody CongregationDTO congregationRequestDTO) {
        Congregation congregation = new Congregation();
        congregation.setName(congregationRequestDTO.getName());

        // Verificar e criar o endereço, se necessário
        Address address = checkAndCreateAddress(congregationRequestDTO);
        if (address == null) {
            // Se não for possível verificar ou criar o endereço, retorne uma resposta de
            // erro
            return ResponseEntity.badRequest()
                    .body("O ID do endereço ou os detalhes completos do endereço devem ser fornecidos.");
        }

        // Defina o endereço na congregação
        congregation.setAddress(address);

        // Obtenha os membros da congregação (simulação)
        List<Long> memberIds = congregationRequestDTO.getMemberIds();
        List<Person> members = getMembers(memberIds);
        if (members == null) {
            // Se não for possível obter os membros, retorne uma resposta de erro
            return ResponseEntity.badRequest()
                    .body("Não foi possível obter os membros associados à congregação.");
        }

        // Crie a congregação
        Congregation createdCongregation = congregationService.createCongregation(congregation);

        // Retorne uma resposta de sucesso com a congregação criada
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCongregation);
    }

    @PutCongregationRequestBody
    @PutMapping("/congregations/{id}")
    public ResponseEntity<Congregation> updateCongregation(@PathVariable Long id,
            @RequestBody CongregationDTO congregationDTO) {
        // Verificar se a congregação existe
        Congregation existingCongregation = congregationService.getCongregationById(id);
        if (existingCongregation == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizar os campos da congregação com os dados fornecidos no DTO
        if (congregationDTO.getName() != null)
            existingCongregation.setName(congregationDTO.getName());

        // Carregar e definir o novo endereço com base no ID fornecido
        Address address = addressService.getAddressById(congregationDTO.getAddressId());
        existingCongregation.setAddress(address);

        // Carregar e definir os novos membros com base nos IDs fornecidos
        List members = congregationDTO.getMemberIds();
        existingCongregation.setMembers(members);

        // Atualizar a congregação no banco de dados
        Congregation updatedCongregation = congregationService.updateCongregation(id, existingCongregation);
        return ResponseEntity.ok(updatedCongregation);
    }

    @GetByIdCongregationRequestBody
    @GetMapping("/{id}")
    public ResponseEntity<Congregation> getCongregationById(@PathVariable("id") Long id) {
        Congregation congregation = congregationService.getCongregationById(id);
        if (congregation != null) {
            return ResponseEntity.ok(congregation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteCongregationRequestBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCongregation(@PathVariable("id") Long id) {
        boolean response = congregationService.deleteCongregation(id);

        if (response) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Address checkAndCreateAddress(CongregationDTO congregationRequestDTO) {
        Long addressId = congregationRequestDTO.getAddressId();
        Address address = null;

        if (addressId != null) {
            address = addressService.getAddressById(addressId);
            if (address == null) {
                // Se o endereço não for encontrado, retorne null
                return null;
            }
        } else {
            // Se os detalhes completos do endereço foram fornecidos
            AddressDTO addressDTO = congregationRequestDTO.getAddressDetails();
            if (addressDTO != null) {
                // Converter AddressDTO para Address e salvar o novo endereço
                address = addressService.convertToAddress(addressDTO);
                address = addressService.createAddress(address);
            } else {
                // Se nenhum ID de endereço nem detalhes de endereço foram fornecidos, retorne
                // null
                return null;
            }
        }
        return address;
    }

    private List<Person> getMembers(List<Long> memberIds) {
        List<Person> members = new ArrayList<>();
        for (Long memberId : memberIds) {
            // Simule a criação de um objeto Person com base no ID do membro
            Person member = new Person();
            member.setId(memberId);
            members.add(member);
        }
        return members;
    }

}
