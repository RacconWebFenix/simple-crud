package com.raccon.simplecrud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AddressDTO {

    @Schema(description = "ID do endereço")
    private Long id;

    @Schema(description = "ID da pessoa associada a este endereço")
    private Long personId;

    @Schema(description = "Rua")
    private String street;

    @Schema(description = "Cidade")
    private String city;

    @Schema(description = "Estado")
    private String state;

    @Schema(description = "Código postal")
    private String postalCode;
}
