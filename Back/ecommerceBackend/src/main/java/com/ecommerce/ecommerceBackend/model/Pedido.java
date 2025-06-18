package com.ecommerce.ecommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    private Integer idPedido;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column (name = "estado", nullable = false, length = 255)
    private String estado;

    @Column (name = "total", nullable = false, length = 255)
    private Double total;

    // Llaves foraneas
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "iddireccion", referencedColumnName = "iddireccion", nullable = false)
    private Direccion direccion;
}
