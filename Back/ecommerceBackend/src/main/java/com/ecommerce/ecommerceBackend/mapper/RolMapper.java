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
}
