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
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private Integer idDireccion;

    @Column(name = "nombre_destinatario", nullable = false, length = 255)
    private String nombreDestinatario;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "ciudad", nullable = false, length = 255)
    private String ciudad;

    @Column(name = "departamento", nullable = false, length = 255)
    private String departamento;

    @Column(name = "pais", nullable = false, length = 255)
    private String pais;

    @Column(name = "codigo_postal", nullable = false, length = 10)
    private String codigoPostal;

    // Llaves foraneas (Usuarios tienen una direccion)
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    private Usuario usuario;
}
