package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.RolDTO;
import com.ecommerce.ecommerceBackend.model.Rol;
import java.util.List;

public interface RolService {
    List<Rol> getAllRoles();
    RolDTO getRolesById(Integer idRol);
}
