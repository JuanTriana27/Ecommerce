package com.ecommerce.ecommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idusuario", nullable = false)
    private Integer idUsuario;

    @Column (name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column (name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Llaves Foraneas (Muchos Usuarios Pertecen a un Rol)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", nullable = false)
    private Rol rol;
}
