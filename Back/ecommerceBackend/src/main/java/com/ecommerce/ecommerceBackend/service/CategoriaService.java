package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.CategoriaDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCategoriaRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCategoriaResponse;
import com.ecommerce.ecommerceBackend.model.Categoria;
import java.util.List;

public interface CategoriaService {
    // Obtener todas las categorias
    List<Categoria> getAllCategorias();

    // Consultar por ID
    CategoriaDTO getCategoriaById(Integer idCategoria);

    // Crear Categoria
    CreateCategoriaResponse createCategoriaResponse(CreateCategoriaRequest createCategoriaRequest) throws Exception;

    // Actualizar Categoria
    CreateCategoriaResponse updateCategoria(Integer idCategoria, CreateCategoriaRequest request) throws Exception;

    // Eliminar Categoria
    void deleteCategoria(Integer idCategoria) throws Exception;
}
