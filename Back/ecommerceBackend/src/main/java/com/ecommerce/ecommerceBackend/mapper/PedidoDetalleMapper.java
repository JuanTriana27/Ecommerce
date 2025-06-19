package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.PedidoDetalleDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoDetalleRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoDetalleResponse;
import com.ecommerce.ecommerceBackend.model.PedidoDetalle;

public class PedidoDetalleMapper {

    // Metodo modelToDTO
    public static PedidoDetalleDTO modelToDTO(PedidoDetalle pedidoDetalle) {
        return PedidoDetalleDTO.builder()
                .idDetalle(pedidoDetalle.getIdDetalle())
                .cantidad(pedidoDetalle.getCantidad())
                .precioUnitario(pedidoDetalle.getPrecioUnitario())
                .idPedido(pedidoDetalle.getPedido() != null ?
                        pedidoDetalle.getPedido().getIdPedido() : null)
                .idProducto(pedidoDetalle.getProducto() != null ?
                        pedidoDetalle.getProducto().getIdProducto() : null)
                .build();
    }

    // Metodo Request
    public static PedidoDetalle createRequestToModel(CreatePedidoDetalleRequest request) {
        return PedidoDetalle.builder()
                .idDetalle(request.getIdDetalle())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();
    }

    // Metodo Response
    public static CreatePedidoDetalleResponse modelToCreateResponse(PedidoDetalle pedidoDetalle){
        return CreatePedidoDetalleResponse.builder()
                .idDetalle(pedidoDetalle.getIdDetalle())
                .cantidad(pedidoDetalle.getCantidad())
                .precioUnitario(pedidoDetalle.getPrecioUnitario())
                .idPedido(pedidoDetalle.getPedido() != null ?
                        pedidoDetalle.getPedido().getIdPedido() : null)
                .idProducto(pedidoDetalle.getProducto() != null ?
                        pedidoDetalle.getProducto().getIdProducto() : null)
                .build();
    }
}
