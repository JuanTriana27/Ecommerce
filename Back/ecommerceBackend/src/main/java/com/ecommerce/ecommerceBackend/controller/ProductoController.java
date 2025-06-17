package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.ProductoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateProductoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateProductoResponse;
import com.ecommerce.ecommerceBackend.model.Producto;
import com.ecommerce.ecommerceBackend.service.impl.ProductoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServiceImpl productoService;

    // Obtener Todos los Productos
    @GetMapping("/todos")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    // Obtener Productos por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            ProductoDTO productoDTO = productoService.getProductoById(id);
            return ResponseEntity.ok(productoDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Producto con ID " + id + " no encontrado.");
        }
    }

    // Guardar Producto
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarProducto(@RequestBody CreateProductoRequest request) {
        try {
            CreateProductoResponse response = productoService.createProductoResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Producto Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Producto
    @PutMapping("/update/{id}")
    public ResponseEntity<?> productoUpdate(@PathVariable Integer id, @RequestBody CreateProductoRequest request) {
        try {
            CreateProductoResponse response = productoService.updateProducto(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Producto: " + e.getMessage());
        }
    }

    // Eliminar Producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id){
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Producto: " + e.getMessage());
        }
    }
}
