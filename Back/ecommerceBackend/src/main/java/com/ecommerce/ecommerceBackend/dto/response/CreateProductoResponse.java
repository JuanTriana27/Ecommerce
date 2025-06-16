package com.ecommerce.ecommerceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoResponse {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    // ID de relacion
    private Integer idCategoria;
}
