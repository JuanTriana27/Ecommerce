package com.ecommerce.ecommerceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarritoItemRequest {
    private Integer idCarritoItem;
    private Integer cantidad;

    // ID Relacion
    private Integer idProducto;
    private Integer idCarrito;
}
