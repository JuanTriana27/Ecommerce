package com.ecommerce.ecommerceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePedidoRequest {
    private Integer idPedido;
    private LocalDate fecha;
    private String estado;
    private Double total;

    // IDs Relacion
    private Integer idUsuario;
    private Integer idDireccion;
}
