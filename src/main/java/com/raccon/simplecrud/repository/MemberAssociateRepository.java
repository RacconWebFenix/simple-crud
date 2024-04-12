package com.raccon.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raccon.simplecrud.model.MemberAssociation.MemberAssociation;
import com.raccon.simplecrud.model.MemberAssociation.PersonCongregationId;

public interface MemberAssociateRepository extends JpaRepository<MemberAssociation, PersonCongregationId> {

}
