package com.raccon.simplecrud.dto;

import com.raccon.simplecrud.model.user.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterDTO(@Schema(description = "Endereço de e-mail", required = true) String email,
        @Schema(description = "Senha", required = true) String password,
        @Schema(description = "Role (ADMIN, USER)", required = true) UserRole role) {

}
