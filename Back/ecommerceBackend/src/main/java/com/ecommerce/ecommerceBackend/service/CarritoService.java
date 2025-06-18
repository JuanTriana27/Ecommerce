package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.CarritoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoResponse;
import com.ecommerce.ecommerceBackend.model.Carrito;

import java.util.List;

public interface CarritoService {

    // Obtener todos
    List<Carrito> getAllCarritos();

    // Consultar por ID
    CarritoDTO getCarritoById(Integer idCarrito);

    // Crear Carrito
    CreateCarritoResponse createCarritoResponse(CreateCarritoRequest createCarritoRequest) throws Exception;

    // Actualizar Carrito
    CreateCarritoResponse updateCarrito(Integer idCarrito, CreateCarritoRequest request) throws Exception;

    // Eliminar Carrito
    void deleteCarrito(Integer idCarrito) throws Exception;
}
