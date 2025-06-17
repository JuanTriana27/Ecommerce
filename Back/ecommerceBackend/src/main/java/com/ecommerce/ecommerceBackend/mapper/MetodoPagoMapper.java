package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.MetodoPagoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateMetodoPagoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateMetodoPagoResponse;
import com.ecommerce.ecommerceBackend.model.MetodoPago;

public class MetodoPagoMapper {

    // Metodo modelToDTO
    public static MetodoPagoDTO modelToDTO(MetodoPago metodoPago) {
        return MetodoPagoDTO.builder()
                .idMetodoPago(metodoPago.getIdMetodoPago())
                .tipo(metodoPago.getTipo())
                .numeroTarjeta(metodoPago.getNumeroTarjeta())
                .nombreTitular(metodoPago.getNombreTitular())
                .fechaExpiracion(metodoPago.getFechaExpiracion())
                .idUsuario(metodoPago.getUsuario() !=null ?
                        metodoPago.getUsuario().getIdUsuario() : null)
                .build();
    }

    // Metodo Request
    public static MetodoPago createRequestToModel (CreateMetodoPagoRequest request) {
        return MetodoPago.builder()
                .idMetodoPago(request.getIdMetodoPago())
                .tipo(request.getTipo())
                .numeroTarjeta(request.getNumeroTarjeta())
                .nombreTitular(request.getNombreTitular())
                .fechaExpiracion(request.getFechaExpiracion())
                .build();
    }

    // Metodo Response
    public static CreateMetodoPagoResponse modelToCreateResponse(MetodoPago metodoPago) {
        return CreateMetodoPagoResponse.builder()
                .idMetodoPago(metodoPago.getIdMetodoPago())
                .tipo(metodoPago.getTipo())
                .numeroTarjeta(metodoPago.getNumeroTarjeta())
                .nombreTitular(metodoPago.getNombreTitular())
                .fechaExpiracion(metodoPago.getFechaExpiracion())
                .idUsuario(metodoPago.getUsuario() != null ?
                        metodoPago.getUsuario().getIdUsuario() : null)
                .build();
    }
}
