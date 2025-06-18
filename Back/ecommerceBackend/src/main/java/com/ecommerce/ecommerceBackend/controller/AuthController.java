package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.request.LoginRequest;
import com.ecommerce.ecommerceBackend.dto.response.LoginResponse;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.service.UsuarioService;
import com.ecommerce.ecommerceBackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(request.getEmail());

            if (passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
                String token = jwtUtil.generateToken(usuario.getEmail());
                return ResponseEntity.ok(new LoginResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en login: " + e.getMessage());
        }
    }
}
