package com.raccon.simplecrud.dto;

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
public class PhoneDTO {
    @Schema(description = "ID do telefone")
    private Long id;
    @Schema(description = "ID da pessoa associada ao telefone")
    private Long personId;
    @Schema(description = "Número")
    private String phone;
}
