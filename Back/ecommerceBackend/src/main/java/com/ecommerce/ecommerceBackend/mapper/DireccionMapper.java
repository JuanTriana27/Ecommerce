package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.DireccionDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateDireccionRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateDireccionResponse;
import com.ecommerce.ecommerceBackend.model.Direccion;

public class DireccionMapper {

    // Metodo modelToDTO
    public static DireccionDTO modelToDTO(Direccion direccion) {
        return DireccionDTO.builder()
                .idDireccion(direccion.getIdDireccion())
                .nombreDestinatario(direccion.getNombreDestinatario())
                .direccion(direccion.getDireccion())
                .ciudad(direccion.getCiudad())
                .departamento(direccion.getDepartamento())
                .pais(direccion.getDepartamento())
                .codigoPostal(direccion.getCodigoPostal())
                .idUsuario(direccion.getUsuario() !=null ?
                        direccion.getUsuario().getIdUsuario() : null)
                .build();
    }

    // Metodo Request
    public static Direccion createRequestToModel(CreateDireccionRequest request) {
        return Direccion.builder()
                .idDireccion(request.getIdDireccion())
                .nombreDestinatario(request.getNombreDestinatario())
                .direccion(request.getDireccion())
                .ciudad(request.getCiudad())
                .departamento(request.getDepartamento())
                .pais(request.getPais())
                .codigoPostal(request.getCodigoPostal())
                .build();
    }

    // Metodo Response
    public static CreateDireccionResponse modelToCreateResponse(Direccion direccion) {
        return CreateDireccionResponse.builder()
                .idDireccion(direccion.getIdDireccion())
                .nombreDestinatario(direccion.getNombreDestinatario())
                .direccion(direccion.getDireccion())
                .ciudad(direccion.getCiudad())
                .departamento(direccion.getDepartamento())
                .pais(direccion.getPais())
                .codigoPostal(direccion.getCodigoPostal())
                .idUsuario(direccion.getUsuario() != null ?
                        direccion.getUsuario().getIdUsuario() : null)
                .build();
    }
}
