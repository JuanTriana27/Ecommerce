package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.UsuarioDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateUsuarioRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateUsuarioResponse;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Obtener todos los usuarios (GET)
    @GetMapping("/todos")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    // Obtener Usuario por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuario con ID " + id + " no encontrado.");
        }
    }

    // Metodo para guardar un usuario
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarUsuario(@RequestBody CreateUsuarioRequest request) {
        try {
            CreateUsuarioResponse createUsuarioResponse = usuarioService.createUsuarioResponse(request);
            return new ResponseEntity<>(createUsuarioResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Usuario Nuevo: " + e.getMessage());
        }
    }

    // Metodo para actualizar un usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<?> usuarioUpdate(@PathVariable Integer id, @RequestBody CreateUsuarioRequest request) {
        try {
            CreateUsuarioResponse createUsuarioResponse = usuarioService.updateUsuario(id, request);
            return new ResponseEntity<>(createUsuarioResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Usuario: " + e.getMessage());
        }
    }

    // Metodo para eliminar un Usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Usuario: " + e.getMessage());
        }
    }
}
