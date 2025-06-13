package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.CategoriaDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCategoriaRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCategoriaResponse;
import com.ecommerce.ecommerceBackend.mapper.CategoriaMapper;
import com.ecommerce.ecommerceBackend.model.Categoria;
import com.ecommerce.ecommerceBackend.service.impl.CategoriaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaServiceImpl categoriaService;

    // Obtener Todas las Categorias
    @GetMapping("/todos")
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }

    // Obtener Categoria por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            CategoriaDTO categoriaDTO = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(categoriaDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Categoria con ID " + id + " no encontrado.");
        }
    }

    // Guardar Categoria
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?> guardarCategoria(@RequestBody CreateCategoriaRequest request) {
        try {
            CreateCategoriaResponse createCategoriaResponse = categoriaService.createCategoriaResponse(request);
            return new ResponseEntity<>(createCategoriaResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Categoria Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Categoria
    @PutMapping("/update/{id}")
    public ResponseEntity<?> categoriaUpdate(@PathVariable Integer id, @RequestBody CreateCategoriaRequest request) {
        try {
            CreateCategoriaResponse createCategoriaResponse = categoriaService.updateCategoria(id, request);
            return new ResponseEntity<>(createCategoriaResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Categoria: " + e.getMessage());
        }
    }

    // Eliminar Categoria
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id){
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.ok("Categoria Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Categoria: " + e.getMessage());
        }
    }
}
