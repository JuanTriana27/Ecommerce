package com.ecommerce.ecommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDetalleDTO {
    private Integer idDetalle;
    private Integer cantidad;
    private Double precioUnitario;

    // IDs de relacion
    private Integer idPedido;
    private Integer idProducto;
}
