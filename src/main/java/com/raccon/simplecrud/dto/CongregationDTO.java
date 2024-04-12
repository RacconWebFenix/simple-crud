package com.raccon.simplecrud.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CongregationDTO {
    @Schema(description = "ID da Congregação")
    private Long id;

    @Schema(description = "Nome da Congregação")
    private String name;

    @Schema(description = "ID do endereço da Congregação")
    private Long addressId;

    @Schema(description = "Detalhes do endereço da Congregação")
    private AddressDTO address; // Alterado para AddressDTO

    @Schema(description = "IDs dos membros associados à Congregação")
    private List<Long> memberIds;
}
