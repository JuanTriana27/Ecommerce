package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.MetodoPagoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateMetodoPagoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateMetodoPagoResponse;
import com.ecommerce.ecommerceBackend.model.MetodoPago;

import java.util.List;

public interface MetodoPagoService {

    // Obtener todos los metodos de pago
    List<MetodoPago> getAllMetodosPago();

    // Consultar por ID
    MetodoPagoDTO getMetodoPagoById(Integer idMetodoPago);

    // Crear metodo pago
    CreateMetodoPagoResponse createMetodoPagoResponse(CreateMetodoPagoRequest createMetodoPagoRequest) throws Exception;

    // Actualizar metodo pago
    CreateMetodoPagoResponse updateMetodoPago(Integer idMetodoPago, CreateMetodoPagoRequest request) throws Exception;

    // Eliminar Metodo pago
    void deleteMetodoPago(Integer idMetodoPago) throws Exception;
}
