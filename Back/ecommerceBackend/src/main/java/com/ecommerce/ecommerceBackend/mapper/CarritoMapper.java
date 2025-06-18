package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.CarritoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoResponse;
import com.ecommerce.ecommerceBackend.dto.response.CreateDireccionResponse;
import com.ecommerce.ecommerceBackend.model.Carrito;

public class CarritoMapper {

    // Metodo modelToDTO
    public static CarritoDTO modelToDTO(Carrito carrito) {
        return CarritoDTO.builder()
                .idCarrito(carrito.getIdCarrito())
                .idUsuario(carrito.getUsuario() !=null ?
                        carrito.getUsuario().getIdUsuario() : null)
                .build();
    }

    // Metodo Request
    public static Carrito createRequestToModel(CreateCarritoRequest request) {
        return Carrito.builder()
                .idCarrito(request.getIdCarrito())
                .build();
    }

    // Metodo Response
    public static CreateCarritoResponse modelToCreateResponse(Carrito carrito) {
        return CreateCarritoResponse.builder()
                .idCarrito(carrito.getIdCarrito())
                .idUsuario(carrito.getUsuario() !=null ?
                        carrito.getUsuario().getIdUsuario() : null)
                .build();
    }
}
