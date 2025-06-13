package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.RolDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateRolRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateRolResponse;
import com.ecommerce.ecommerceBackend.model.Rol;

public class RolMapper {

    // Metodo modelToDTO
    public static RolDTO modelToDTO(Rol rol) {
        return RolDTO.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .build();
    }

    // Metodo dtoToModel
    public static Rol dtoRol (RolDTO rolDTO) {
        return Rol.builder()
                .idRol(rolDTO.getIdRol())
                .nombre(rolDTO.getNombre())
                .build();
    }

    // Metodo Request
    public static Rol createRequestToModel(CreateRolRequest request) {
        return Rol.builder()
                .idRol(request.getIdRol())
                .nombre(request.getNombre())
                .build();
    }

    // Metodo Response
    public static Rol createResponseToModel(CreateRolResponse rolResponse) {
        return Rol.builder()
                .idRol(rolResponse.getIdRol())
                .nombre(rolResponse.getNombre())
                .build();
    }
}
