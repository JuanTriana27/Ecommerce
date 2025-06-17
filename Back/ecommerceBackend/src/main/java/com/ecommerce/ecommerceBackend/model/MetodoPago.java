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
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmetodopago")
    private Integer idMetodoPago;

    @Column(name = "tipo", nullable = false, length = 255)
    private String tipo;

    @Column(name = "numero_tarjeta", nullable = false, length = 255)
    private String numeroTarjeta;

    @Column(name = "nombre_titular", nullable = false, length = 255)
    private String nombreTitular;

    @Column(name = "fecha_expiracion", nullable = false, length = 255)
    private LocalDate fechaExpiracion;

    // Llaves foraneas (Usuarios tienen metodos de pago"
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    private Usuario usuario;
}
