package com.raccon.simplecrud.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationDTO(
        @Schema(description = "Endereço de e-mail", required = true) String email,
        @Schema(description = "Senha", required = true) String password) {
}
