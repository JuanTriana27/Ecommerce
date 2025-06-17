package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.DireccionDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateDireccionRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateDireccionResponse;
import com.ecommerce.ecommerceBackend.model.Direccion;
import com.ecommerce.ecommerceBackend.service.impl.DireccionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direccion")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionServiceImpl direccionService;

    // Obtener Todas
    @GetMapping("/todos")
    public List<Direccion> getAllDirecciones(){
        return direccionService.getAllDirecciones();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            DireccionDTO direccionDTO = direccionService.getDireccionById(id);
            return ResponseEntity.ok(direccionDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Direccion con ID " + id + " no encontrado.");
        }
    }

    // Guardar Direccion
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarDireccion(@RequestBody CreateDireccionRequest request) {
        try{
            CreateDireccionResponse response = direccionService.createDireccionResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Direccion Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Direccion
    @PutMapping("/update/{id}")
    public ResponseEntity<?> direccionUpdate(@PathVariable Integer id, @RequestBody CreateDireccionRequest request) {
        try{
            CreateDireccionResponse response = direccionService.updateDireccion(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Direccion: " + e.getMessage());
        }
    }

    // Eliminar Direccion
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDireccion(@PathVariable Integer id){
        try{
            direccionService.deleteDireccion(id);
            return ResponseEntity.ok("Direccion Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Direccion: " + e.getMessage());
        }
    }
}
