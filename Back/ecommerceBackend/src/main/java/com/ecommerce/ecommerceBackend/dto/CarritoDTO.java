package com.ecommerce.ecommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    private Integer idCarrito;

    // ID relacion
    private Integer idUsuario;
}
