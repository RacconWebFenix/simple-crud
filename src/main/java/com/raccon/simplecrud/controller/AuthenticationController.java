package com.raccon.simplecrud.controller;

import org.springframework.web.bind.annotation.RestController;

import com.raccon.simplecrud.infra.TokenService;
import com.raccon.simplecrud.model.user.AuthenticationDTO;
import com.raccon.simplecrud.model.user.LoginResponseDTO;
import com.raccon.simplecrud.model.user.RegisterDTO;
import com.raccon.simplecrud.model.user.User;
import com.raccon.simplecrud.repository.userRepository.UserRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticateMananger;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticateMananger.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encriptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
