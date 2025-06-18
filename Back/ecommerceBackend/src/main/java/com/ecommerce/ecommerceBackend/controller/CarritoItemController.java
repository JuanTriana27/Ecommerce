package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.CarritoItemDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoItemRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoItemResponse;
import com.ecommerce.ecommerceBackend.model.CarritoItem;
import com.ecommerce.ecommerceBackend.service.impl.CarritoItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carrito-item")
@RequiredArgsConstructor
public class CarritoItemController {

    private final CarritoItemServiceImpl carritoItemService;

    // Obtener Todos
    @GetMapping("/todos")
    public List<CarritoItem> getAllCarritoItems(){
        return carritoItemService.getAllCarritoItems();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            CarritoItemDTO carritoItemDTO = carritoItemService.getCarritoItemById(id);
            return ResponseEntity.ok(carritoItemDTO);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Carrito Item con ID " + id + " no encontrado.");
        }
    }

    // Guardar Carrito
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?>guardarCarritoItem(@RequestBody CreateCarritoItemRequest request) {
        try{
            CreateCarritoItemResponse response = carritoItemService.createCarritoItemResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Carrito Item Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Carrito
    @PutMapping("/update/{id}")
    public ResponseEntity<?>carritoItemUpdate(@PathVariable Integer id, @RequestBody CreateCarritoItemRequest request) {
        try{
            CreateCarritoItemResponse response = carritoItemService.updateCarritoItem(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Carrito Item: " + e.getMessage());
        }
    }

    // Eliminar Carrito Item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCarritoItem(@PathVariable Integer id){
        try{
            carritoItemService.deleteCarritoItem(id);
            return ResponseEntity.ok("Carrito Item Eliminado Correctamente");
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Carrito Item: " + e.getMessage());
        }
    }
}
