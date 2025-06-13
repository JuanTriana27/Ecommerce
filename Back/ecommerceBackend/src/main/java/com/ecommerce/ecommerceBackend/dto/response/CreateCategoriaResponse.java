package com.ecommerce.ecommerceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriaResponse {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
}
