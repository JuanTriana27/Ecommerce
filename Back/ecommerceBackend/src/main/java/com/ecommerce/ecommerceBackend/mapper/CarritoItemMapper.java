package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.CarritoItemDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoItemRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoItemResponse;
import com.ecommerce.ecommerceBackend.model.CarritoItem;

public class CarritoItemMapper {

    // Metodo modelToDTO
    public static CarritoItemDTO modelToDTO(CarritoItem carritoItem) {
        return CarritoItemDTO.builder()
                .idCarritoItem(carritoItem.getIdCarritoItem())
                .cantidad(carritoItem.getCantidad())
                .idCarrito(carritoItem.getCarrito() != null ?
                        carritoItem.getCarrito().getIdCarrito() : null)
                .idProducto(carritoItem.getProducto() != null ?
                        carritoItem.getProducto().getIdProducto() : null)
                .build();
    }

    // Metodo Request
    public static CarritoItem createRequestToModel(CreateCarritoItemRequest request){
        return CarritoItem.builder()
                .idCarritoItem(request.getIdCarritoItem())
                .cantidad(request.getCantidad())
                .build();
    }

    // Metodo Response
    public static CreateCarritoItemResponse modelToCreateResponse(CarritoItem carritoItem){
        return CreateCarritoItemResponse.builder()
                .idCarritoItem(carritoItem.getIdCarritoItem())
                .cantidad(carritoItem.getCantidad())
                .idCarrito(carritoItem.getCarrito() != null ?
                        carritoItem.getCarrito().getIdCarrito() : null)
                .idProducto(carritoItem.getProducto() != null ?
                        carritoItem.getProducto().getIdProducto() : null)
                .build();
    }
}
