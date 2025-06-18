package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.CarritoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoResponse;
import com.ecommerce.ecommerceBackend.model.Carrito;
import com.ecommerce.ecommerceBackend.service.CarritoService;
import com.ecommerce.ecommerceBackend.service.impl.CarritoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoServiceImpl carritoService;

    // Obtener Todos
    @GetMapping("/todos")
    public List<Carrito> getAllCarritos(){
        return carritoService.getAllCarritos();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try {
            CarritoDTO carritoDTO = carritoService.getCarritoById(id);
            return ResponseEntity.ok(carritoDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Carrito con ID " + id + " no encontrado.");
        }
    }

    // Guardar Carrito
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarCarrito(@RequestBody CreateCarritoRequest request) {
        try {
            CreateCarritoResponse response = carritoService.createCarritoResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Carrito Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Carrito
    @PutMapping("/update/{id}")
    public ResponseEntity<?> carritoUpdate(@PathVariable Integer id, @RequestBody CreateCarritoRequest request) {
        try {
            CreateCarritoResponse response = carritoService.updateCarrito(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Carrito: " + e.getMessage());
        }
    }

    // Eliminar Carrito
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCarrito(@PathVariable Integer id){
        try {
            carritoService.deleteCarrito(id);
            return ResponseEntity.ok("Carrito Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Carrito: " + e.getMessage());
        }
    }
}
