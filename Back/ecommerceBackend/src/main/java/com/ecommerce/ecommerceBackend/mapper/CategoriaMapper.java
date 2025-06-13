package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.CategoriaDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCategoriaRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCategoriaResponse;
import com.ecommerce.ecommerceBackend.model.Categoria;

public class CategoriaMapper {

    // Metodo modelToDTO
    public static CategoriaDTO modelToDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    // Metodo dtoToModel
    public static Categoria dtoCategoria (CategoriaDTO categoriaDTO){
        return Categoria.builder()
                .idCategoria(categoriaDTO.getIdCategoria())
                .nombre(categoriaDTO.getNombre())
                .descripcion(categoriaDTO.getDescripcion())
                .build();
    }

    // Metodo Request
    public static Categoria createRequestToModel (CreateCategoriaRequest request){
        return Categoria.builder()
                .idCategoria(request.getIdCategoria())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    // Metodo Response
    public static CreateCategoriaResponse modelToCreateResponse(Categoria categoria) {
        return CreateCategoriaResponse.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
