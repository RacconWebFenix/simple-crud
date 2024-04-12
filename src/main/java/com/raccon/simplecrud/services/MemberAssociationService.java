package com.raccon.simplecrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccon.simplecrud.model.MemberAssociation.MemberAssociation;
import com.raccon.simplecrud.model.MemberAssociation.PersonCongregationId;
import com.raccon.simplecrud.repository.MemberAssociateRepository;

@Service
public class MemberAssociationService {

    @Autowired
    private MemberAssociateRepository memberAssociationRepository;

    public void associateMember(Long personId, Long congregationId) {
        PersonCongregationId id = new PersonCongregationId(personId, congregationId);
        MemberAssociation memberAssociation = new MemberAssociation(id);
        memberAssociationRepository.save(memberAssociation);
    }

    public void disassociateMember(Long personId, Long congregationId) {
        PersonCongregationId id = new PersonCongregationId(personId, congregationId);
        memberAssociationRepository.deleteById(id);
    }
}
