package org.deliverymatch.backend.controller.auth;

import org.deliverymatch.backend.dto.LoginDTO;
import org.deliverymatch.backend.dto.RegisterUserDTO;
import org.deliverymatch.backend.dto.UserDTO;
import org.deliverymatch.backend.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerDTO) {
        return ResponseEntity.ok(authService.registerUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.loginUser(loginDTO);
        return ResponseEntity.ok(token);
    }
}