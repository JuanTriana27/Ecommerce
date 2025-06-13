package com.ecommerce.ecommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
}
