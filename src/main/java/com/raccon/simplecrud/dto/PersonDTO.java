package com.raccon.simplecrud.dto;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @Schema(description = "Nome da pessoa", example = "John Doe")
    private String name;

    @Schema(description = "CPF da pessoa", example = "12345678910")
    private String cpf;

    @Schema(description = "Data de nascimento da pessoa", example = "1990-05-20")
    private Date birthDate;

    @Schema(description = "Endereço de e-mail da pessoa", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Função da pessoa", example = "COOPERADOR")
    private String personFunction;

    @Schema(description = "Instrumento da pessoa", example = "PIANO")
    private String instrument;

    @Schema(description = "Lista de endereços da pessoa")
    private List<AddressDTO> address;

    @Schema(description = "Lista de números de telefone da pessoa")
    private List<PhoneDTO> phoneNumbers;

    @Schema(description = "Lista de congregações associadas à pessoa")
    private List<CongregationDTO> congregations;
}
