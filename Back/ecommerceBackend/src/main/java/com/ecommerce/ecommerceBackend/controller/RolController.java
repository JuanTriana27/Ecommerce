package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.RolDTO;
import com.ecommerce.ecommerceBackend.model.Rol;
import com.ecommerce.ecommerceBackend.service.impl.RolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RolController {

    private final RolServiceImpl rolService;

    // Obtener todos los roles
    @RequestMapping("/todos")
    public List<Rol> getAllRoles(){
        return rolService.getAllRoles();
    }

    // Buscar por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Integer id){
        try {
            RolDTO rolDTO = rolService.getRolesById(id);
            return ResponseEntity.ok(rolDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Rol con ID " + id + " no encontrado.");
        }
    }
}
