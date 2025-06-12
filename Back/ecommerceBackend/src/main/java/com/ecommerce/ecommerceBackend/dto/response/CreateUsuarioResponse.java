package com.ecommerce.ecommerceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUsuarioResponse {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDateTime fechaCreacion;
    private String passwordHash;
    private Integer idRol;
}
