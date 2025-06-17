package com.ecommerce.ecommerceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMetodoPagoResponse {
    private Integer idMetodoPago;
    private String tipo;
    private String numeroTarjeta;
    private String nombreTitular;
    private LocalDate fechaExpiracion;

    // ID Relacion
    private Integer idUsuario;
}
