package com.example.Rpg_Marnes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Rpg_Marnes.model.User.AuthenticationDTO;
import com.example.Rpg_Marnes.model.User.RegisterDTO;
import com.example.Rpg_Marnes.model.User.User;
import com.example.Rpg_Marnes.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed for object='registerDTO'. Errors: ");
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        // Verifique se o login já existe
        if (repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Login already exists");
        }

        // Criptografar a senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        // Criar um novo usuário
        User newUser = new User(data.login(), encryptedPassword, data.role());

        // Salvar o novo usuário no banco de dados
        repository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }
}
