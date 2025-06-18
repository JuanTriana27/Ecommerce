package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.PedidoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoResponse;
import com.ecommerce.ecommerceBackend.model.Pedido;

import java.util.List;

public interface PedidoService {

    // Obtener Todos
    List<Pedido>getAllPedidos();

    // Consultar por ID
    PedidoDTO getPedidoById(Integer idPedido);

    // Crear pedido
    CreatePedidoResponse createPedidoResponse(CreatePedidoRequest request) throws Exception;

    // Actualizar Pedido
    CreatePedidoResponse updatePedido(Integer idPedido, CreatePedidoRequest request) throws Exception;

    // Eliminar Pedido
    void deletePedido(Integer idPedido) throws Exception;
}
