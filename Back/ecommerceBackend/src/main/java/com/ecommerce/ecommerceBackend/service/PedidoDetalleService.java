package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.PedidoDetalleDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoDetalleRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoDetalleResponse;
import com.ecommerce.ecommerceBackend.model.PedidoDetalle;

import java.util.List;

public interface PedidoDetalleService {

    // Obtener Todos
    List<PedidoDetalle>getAllPedidosDetalles();

    // Consultar por ID
    PedidoDetalleDTO getPedidoDetalleById(Integer idDetalle);

    // Crear Detalle
    CreatePedidoDetalleResponse createPedidoDetalleResponse(CreatePedidoDetalleRequest request) throws Exception;

    // Actualizar
    CreatePedidoDetalleResponse updatePedidoDetalle(Integer idDetalle, CreatePedidoDetalleRequest request) throws Exception;

    // Eliminar Detalle
    void deletePedidoDetalle(Integer idDetalle) throws Exception;
}
