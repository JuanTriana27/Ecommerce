package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.ProductoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateProductoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateProductoResponse;
import com.ecommerce.ecommerceBackend.model.Producto;

import java.util.List;

public interface ProductoService {

    // Obtener todos los productos
    List<Producto> getAllProductos();

    // Consultar por ID
    ProductoDTO getProductoById(Integer idProducto);

    // Crear Producto
    CreateProductoResponse createProductoResponse(CreateProductoRequest createProductoRequest) throws Exception;

    // Actualizar Producto
    CreateProductoResponse updateProducto(Integer idProducto, CreateProductoRequest request) throws Exception;

    // Eliminar Producto
    void deleteProducto(Integer idProducto) throws Exception;
}
