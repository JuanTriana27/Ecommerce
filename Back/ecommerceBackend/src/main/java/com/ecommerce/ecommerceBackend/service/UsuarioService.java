package com.ecommerce.ecommerceBackend.service;

import com.ecommerce.ecommerceBackend.dto.UsuarioDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateUsuarioRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateUsuarioResponse;
import com.ecommerce.ecommerceBackend.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    UsuarioDTO getUsuarioById(Integer idUsuario);

    CreateUsuarioResponse createUsuarioResponse(CreateUsuarioRequest createUsuarioRequest) throws Exception;
    CreateUsuarioResponse updateUsuario(Integer idUsuario, CreateUsuarioRequest request) throws Exception;
    void deleteUsuario(Integer idUsuario) throws Exception;
}
