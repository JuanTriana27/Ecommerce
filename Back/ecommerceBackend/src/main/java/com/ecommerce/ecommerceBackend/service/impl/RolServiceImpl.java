package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.RolDTO;
import com.ecommerce.ecommerceBackend.mapper.RolMapper;
import com.ecommerce.ecommerceBackend.model.Rol;
import com.ecommerce.ecommerceBackend.repository.RolRepository;
import com.ecommerce.ecommerceBackend.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    // Obtener Todos los Roles
    @Override
    public List<Rol>getAllRoles(){
        return rolRepository.findAll();
    }

    // Obtener Rol por ID
    @Override
    public RolDTO getRolesById(Integer idRol){

        // Consultar en db los roles por ID
        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idRol));

        // Mapear hacia DTO el resultado que trae el modelo
        RolDTO rolDTO = RolMapper.modelToDTO(rol);

        // Retornar el objeto mapeado a DTO
        return rolDTO;
    }
}
