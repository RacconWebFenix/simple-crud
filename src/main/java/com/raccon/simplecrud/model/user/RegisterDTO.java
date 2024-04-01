package com.raccon.simplecrud.model.user;

public record RegisterDTO(String email, String password, UserRole role) {
    
}
