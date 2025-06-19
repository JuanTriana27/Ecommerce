package com.ecommerce.ecommerceBackend.controller;

import com.ecommerce.ecommerceBackend.dto.PedidoDetalleDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoDetalleRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoDetalleResponse;
import com.ecommerce.ecommerceBackend.model.PedidoDetalle;
import com.ecommerce.ecommerceBackend.service.impl.PedidoDetalleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido-detalle")
@RequiredArgsConstructor
public class PedidoDetalleController {

    private final PedidoDetalleServiceImpl pedidoDetalleService;

    // Obtener Todos
    @GetMapping("/todos")
    public List<PedidoDetalle> getAllPedidosDetalles(){
        return pedidoDetalleService.getAllPedidosDetalles();
    }

    // Obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            PedidoDetalleDTO pedidoDetalleDTO = pedidoDetalleService.getPedidoDetalleById(id);
            return ResponseEntity.ok(pedidoDetalleDTO);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pedido Detalle con ID " + id + " no encontrado.");
        }
    }

    // Guardar
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<?>guardarPedidoDetalle(@RequestBody CreatePedidoDetalleRequest request) {
        try{
            CreatePedidoDetalleResponse response = pedidoDetalleService.createPedidoDetalleResponse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear Pedido Detalle Nuevo: " + e.getMessage());
        }
    }

    // Actualizar Pedido
    @PutMapping("/update/{id}")
    public ResponseEntity<?>pedidoDetalleUpdate(@PathVariable Integer id, @RequestBody CreatePedidoDetalleRequest request) {
        try {
            CreatePedidoDetalleResponse response = pedidoDetalleService.updatePedidoDetalle(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar Pedido Detalle: " + e.getMessage());
        }
    }

    // Eliminar Pedido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePedidoDetalle(@PathVariable Integer id){
        try{
            pedidoDetalleService.deletePedidoDetalle(id);
            return ResponseEntity.ok("Pedido Detalle Eliminado Correctamente");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar Pedido Detalle: " + e.getMessage());
        }
    }
}
