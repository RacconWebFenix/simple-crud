package com.raccon.simplecrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raccon.simplecrud.services.MemberAssociationService;

@RestController
@RequestMapping("api/association")
public class AssociationController {

    @Autowired
    private MemberAssociationService memberAssociationService;

    @PostMapping("/associate/{personId}/{congregationId}")
    public void associateMember(@PathVariable Long personId, @PathVariable Long congregationId) {
        memberAssociationService.associateMember(personId, congregationId);
    }

    @DeleteMapping("/disassociate/{personId}/{congregationId}")
    public void disassociateMember(@PathVariable Long personId, @PathVariable Long congregationId) {
        memberAssociationService.disassociateMember(personId, congregationId);
    }

}
