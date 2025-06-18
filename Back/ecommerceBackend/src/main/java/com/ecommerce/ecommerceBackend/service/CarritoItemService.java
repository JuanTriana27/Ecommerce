package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.CarritoItemDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoItemRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoItemResponse;
import com.ecommerce.ecommerceBackend.model.CarritoItem;

import java.util.List;

public interface CarritoItemService {

    // Obtener todos
    List<CarritoItem> getAllCarritoItems();

    // Consultar por ID
    CarritoItemDTO getCarritoItemById(Integer idCarritoItem);

    // Crear CarritoItem
    CreateCarritoItemResponse createCarritoItemResponse(CreateCarritoItemRequest createCarritoItemRequest) throws Exception;

    // Actualizar carrito
    CreateCarritoItemResponse updateCarritoItem(Integer idCarritoItem, CreateCarritoItemRequest request) throws Exception;

    // Eliminar Carrito
    void deleteCarritoItem(Integer idCarritoItem) throws Exception;
}
