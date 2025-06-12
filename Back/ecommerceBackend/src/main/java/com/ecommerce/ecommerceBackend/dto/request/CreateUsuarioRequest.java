package com.ecommerce.ecommerceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioRequest {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String passwordHash;
    private LocalDateTime fechaCreacion;
    private Integer idRol;
}
