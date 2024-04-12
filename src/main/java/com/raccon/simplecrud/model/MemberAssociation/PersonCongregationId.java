package com.raccon.simplecrud.model.MemberAssociation;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCongregationId implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "congregation_id")
    private Long congregationId;

}
