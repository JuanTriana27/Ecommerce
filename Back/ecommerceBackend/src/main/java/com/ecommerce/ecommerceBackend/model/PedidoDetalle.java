package com.ecommerce.ecommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido_detalles")
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private Integer idDetalle;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column (name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    // Llaves Foraneas
    @ManyToOne
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto", nullable = false)
    private Producto producto;
}
