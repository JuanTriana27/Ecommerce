package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.PedidoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoResponse;
import com.ecommerce.ecommerceBackend.model.Pedido;

public class PedidoMapper {

    // Metodo modelToDTO
    public static PedidoDTO modelToDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .idPedido(pedido.getIdPedido())
                .fecha(pedido.getFecha())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .idUsuario(pedido.getUsuario() != null ?
                        pedido.getUsuario().getIdUsuario() : null)
                .idDireccion(pedido.getDireccion() != null ?
                        pedido.getDireccion().getIdDireccion() : null)
                .build();
    }

    // Metodo Request
    public static Pedido createRequestToModel(CreatePedidoRequest request) {
        return Pedido.builder()
                .idPedido(request.getIdPedido())
                .fecha(request.getFecha())
                .estado(request.getEstado())
                .total(request.getTotal())
                .build();
    }

    // Metodo Response
    public static CreatePedidoResponse modelToCreateResponse(Pedido pedido){
        return CreatePedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .fecha(pedido.getFecha())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .idUsuario(pedido.getUsuario() != null ?
                        pedido.getUsuario().getIdUsuario() : null)
                .idDireccion(pedido.getDireccion() != null ?
                        pedido.getDireccion().getIdDireccion() : null)
                .build();
    }
}
