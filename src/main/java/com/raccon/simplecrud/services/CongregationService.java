package com.raccon.simplecrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.raccon.simplecrud.exception.ResourceNotFoundException;
import com.raccon.simplecrud.model.congregation.Congregation;
import com.raccon.simplecrud.model.phone.PhoneNumber;
import com.raccon.simplecrud.repository.CongregationRepository;

@Service
public class CongregationService {

    @Autowired
    private CongregationRepository congregationRepository;

    public List<Congregation> getAllCongregations() {
        return congregationRepository.findAll();
    }

    public Congregation getCongregationById(Long id) {
        return congregationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Congregation not found with id " + id));
    }

    public Congregation createCongregation(Congregation congregation) {

        return congregationRepository.save(congregation);
    }

    public Congregation updateCongregation(Long id, Congregation congregationDetails) {
        Congregation congregation = congregationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Congregation not found with id " + id));

        // Verificar se os detalhes da congregação fornecidos não são nulos antes de
        // atualizá-los
        if (congregationDetails.getName() != null) {
            congregation.setName(congregationDetails.getName());
        }

        // Atualizar outros campos conforme necessário

        return congregationRepository.save(congregation);
    }

 
     public boolean deleteCongregation(Long id) {
        Optional<Congregation> congregation = congregationRepository.findById(id);
        if (congregation.isPresent()) {
            congregationRepository.delete(congregation.get());
            return true;
        } else {
            return false; 
        }
    }

}
