package com.ecommerce.ecommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {
    private Integer idDireccion;
    private String nombreDestinatario;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String pais;
    private String codigoPostal;

    // ID relacion
    private Integer idUsuario;
}
