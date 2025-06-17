package com.ecommerce.ecommerceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMetodoPagoRequest {
    private Integer idMetodoPago;
    private String tipo;
    private String numeroTarjeta;
    private String nombreTitular;
    private LocalDate fechaExpiracion;

    // ID Relacion
    private Integer idUsuario;
}
