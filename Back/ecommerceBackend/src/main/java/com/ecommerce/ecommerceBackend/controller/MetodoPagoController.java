package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.MetodoPagoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateMetodoPagoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateMetodoPagoResponse;
import com.ecommerce.ecommerceBackend.model.MetodoPago;
import com.ecommerce.ecommerceBackend.service.impl.MetodoPagoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("metodo-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoServiceImpl metodoPagoService;

    // Obtener Todos
    @GetMapping("/todos")
    public List<MetodoPago> getAllMetodosPago(){
        return metodoPagoService.getAllMetodosPago();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            MetodoPagoDTO metodoPagoDTO = metodoPagoService.getMetodoPagoById(id);
            return ResponseEntity.ok(metodoPagoDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Metodo de Pago con ID " + id + " no encontrado.");
        }
    }

    // Guardar Metodo
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarMetodoPago(@RequestBody CreateMetodoPagoRequest request) {
        try {
            CreateMetodoPagoResponse response = metodoPagoService.createMetodoPagoResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Metodo de Pago Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Metodo
    @PutMapping("/update/{id}")
    public ResponseEntity<?> metodoPagoUpdate(@PathVariable Integer id, @RequestBody CreateMetodoPagoRequest request) {
        try {
            CreateMetodoPagoResponse response = metodoPagoService.updateMetodoPago(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Metodo de Pago: " + e.getMessage());
        }
    }

    // Eliminar Metodo pago
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMetodoPago(@PathVariable Integer id){
        try {
            metodoPagoService.deleteMetodoPago(id);
            return ResponseEntity.ok("Metodo de Pago Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Metodo de Pago: " + e.getMessage());
        }
    }
}
