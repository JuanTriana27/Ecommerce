package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.ProductoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateProductoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateProductoResponse;
import com.ecommerce.ecommerceBackend.model.Categoria;
import com.ecommerce.ecommerceBackend.model.Producto;

public class ProductoMapper {

    // Metodo modelToDTO
    public static ProductoDTO modelToDTO(Producto producto) {
        return ProductoDTO.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .idCategoria(producto.getCategoria() != null ?
                        producto.getCategoria().getIdCategoria() : null)
                .build();
    }

    // Metodo Request
    public static Producto createRequestToModel(CreateProductoRequest request) {
        return Producto.builder()
                .idProducto(request.getIdProducto())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .categoria(Categoria.builder().idCategoria(request.getIdCategoria()).build())
                .build();
    }

    // Metodo Response
    public static CreateProductoResponse modelToCreateResponse(Producto producto) {
        return CreateProductoResponse.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .idCategoria(
                        producto.getCategoria() != null ?
                                producto.getCategoria().getIdCategoria() : null)
                .build();
    }
}
