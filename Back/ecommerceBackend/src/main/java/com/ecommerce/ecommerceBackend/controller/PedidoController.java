package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.PedidoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoResponse;
import com.ecommerce.ecommerceBackend.model.Pedido;
import com.ecommerce.ecommerceBackend.service.impl.PedidoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoServiceImpl pedidoService;

    // Obtener Todos
    @GetMapping("/todos")
    public List<Pedido>getAllPedidos(){
        return pedidoService.getAllPedidos();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> bucarPorId(@PathVariable Integer id){
        try{
            PedidoDTO pedidoDTO = pedidoService.getPedidoById(id);
            return ResponseEntity.ok(pedidoDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pedido con ID " + id + " no encontrado.");
        }
    }

    // Guardar Pedido
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?>guardarPedido(@RequestBody CreatePedidoRequest request) {
        try{
            CreatePedidoResponse response = pedidoService.createPedidoResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Pedido Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Pedido
    @PutMapping("/update/{id}")
    public ResponseEntity<?>pedidoUpdate(@PathVariable Integer id, @RequestBody CreatePedidoRequest request) {
        try {
            CreatePedidoResponse response = pedidoService.updatePedido(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Pedido: " + e.getMessage());
        }
    }

    // Eliminar Pedido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePedido(@PathVariable Integer id){
        try{
            pedidoService.deletePedido(id);
            return ResponseEntity.ok("Pedido Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Pedido: " + e.getMessage());
        }
    }
}
