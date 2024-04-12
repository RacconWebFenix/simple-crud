package com.raccon.simplecrud.model.MemberAssociation;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_congregation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAssociation {

    @EmbeddedId
    private PersonCongregationId id;

    @Column(name = "person_id", insertable = false, updatable = false)
    private Long personId;

    // Corrija o nome da coluna física para corresponder ao nome na chave composta
    @Column(name = "congregation_id", insertable = false, updatable = false)
    private Long congregationId;

    public MemberAssociation(PersonCongregationId id) {
        this.id = id;
        this.personId = id.getPersonId();
        this.congregationId = id.getCongregationId();
    }

}