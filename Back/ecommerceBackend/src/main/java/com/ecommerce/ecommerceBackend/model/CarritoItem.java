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
@Table(name = "carrito_items")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarritoitem")
    private Integer idCarritoItem;

    @Column(name = "cantidad")
    private Integer cantidad;

    // Llaves Foraneas
    @ManyToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idcarrito", referencedColumnName = "idcarrito", nullable = false)
    private Carrito carrito;
}
