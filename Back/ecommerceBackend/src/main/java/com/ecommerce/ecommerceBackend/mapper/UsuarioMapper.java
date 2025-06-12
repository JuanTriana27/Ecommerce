package com.ecommerce.ecommerceBackend.mapper;

import com.ecommerce.ecommerceBackend.dto.UsuarioDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateUsuarioRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateUsuarioResponse;
import com.ecommerce.ecommerceBackend.model.Rol;
import com.ecommerce.ecommerceBackend.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {

    // Método modelToDTO
    public static UsuarioDTO modelToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .passwordHash(usuario.getPasswordHash())
                .fechaCreacion(usuario.getFechaCreacion())
                .idRol(usuario.getRol() != null ? usuario.getRol().getIdRol() : null)
                .build();
    }

    // Método dtoToModel
    public static Usuario dtoToModel(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .idUsuario(usuarioDTO.getIdUsuario())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .email(usuarioDTO.getEmail())
                .passwordHash(usuarioDTO.getPasswordHash())
                .fechaCreacion(usuarioDTO.getFechaCreacion())
                .rol(usuarioDTO.getIdRol() != null
                        ? Rol.builder().idRol(usuarioDTO.getIdRol()).build()
                        : null)
                .build();
    }

    // Método Request
    public static Usuario createRequestToModel(CreateUsuarioRequest request) {
        return Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .passwordHash(request.getPasswordHash())
                .fechaCreacion(LocalDateTime.now())
                .rol(request.getIdRol() != null
                        ? Rol.builder().idRol(request.getIdRol()).build()
                        : null)
                .build();
    }

    // Método Response
    public static CreateUsuarioResponse modelToCreateResponse(Usuario usuario) {
        return CreateUsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .fechaCreacion(usuario.getFechaCreacion())
                .idRol(usuario.getRol() != null ? usuario.getRol().getIdRol() : null)
                .build();
    }
}