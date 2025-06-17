package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.DireccionDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateDireccionRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateDireccionResponse;
import com.ecommerce.ecommerceBackend.model.Direccion;

import java.util.List;

public interface DireccionService {

    // Obtener todas las direcciones
    List<Direccion> getAllDirecciones();

    // Consultar por ID
    DireccionDTO getDireccionById(Integer idDireccion);

    // Crear Direccion
    CreateDireccionResponse createDireccionResponse(CreateDireccionRequest createDireccionRequest) throws Exception;

    // Actualizar Direccion
    CreateDireccionResponse updateDireccion(Integer idDireccion, CreateDireccionRequest request) throws Exception;

    // Eliminar Direccion
    void deleteDireccion(Integer idDireccion) throws Exception;
}
