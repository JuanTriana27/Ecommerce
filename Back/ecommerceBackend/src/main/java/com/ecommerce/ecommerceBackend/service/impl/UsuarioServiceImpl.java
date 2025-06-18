package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.UsuarioDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateUsuarioRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateUsuarioResponse;
import com.ecommerce.ecommerceBackend.mapper.UsuarioMapper;
import com.ecommerce.ecommerceBackend.model.Rol;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.repository.RolRepository;
import com.ecommerce.ecommerceBackend.repository.UsuarioRepository;
import com.ecommerce.ecommerceBackend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    // Obtener todos los usuarios
    @Override
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    // Obtener usuarios por ID
    @Override
    public UsuarioDTO getUsuarioById(Integer idUsuario){

        // Consultar en db los usuarios por ID
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // Mapear hacia DTO el resultado que trae el modelo
        UsuarioDTO usuarioDTO = UsuarioMapper.modelToDTO(usuario);

        // Retornar el objeto mapeado a DTO
        return usuarioDTO;
    }

    // Crear Usuario en back
    @Override
    public CreateUsuarioResponse createUsuarioResponse (CreateUsuarioRequest CreateUsuarioRequest) throws Exception{
        // Poner validaciones lógicas respecto al DTO

        // Validar que el estado de Usuario no sea nulo
        if (CreateUsuarioRequest == null){
            throw new Exception("El Usuario no puede ser nulo");
        }

        // Validar que el nombre de Usuario no sea nulo
        if (CreateUsuarioRequest.getNombre() == null){
            throw new Exception("El nombre del Usuario no puede ser nulo");
        }

        // Validar que el apellido de Usuario no sea nulo
        if (CreateUsuarioRequest.getApellido() == null){
            throw new Exception("El apellido del Usuario no puede ser nulo");
        }

        // Validar que el email de Usuario no sea nulo
        if (CreateUsuarioRequest.getEmail() == null){
            throw new Exception("El email del Usuario no puede ser nulo");
        }

        // Validar que la contraseña no sea nula
        if (CreateUsuarioRequest.getPasswordHash() == null){
            throw new Exception("La contraseña no puede ser nula");
        }

        // Validar ID de entidad relacionada
        if (CreateUsuarioRequest.getIdRol() == null || CreateUsuarioRequest.getIdRol() <= 0){
            throw new Exception("El ID del Rol es Invalido");
        }

        // Obtener entidad Relacionada
        Rol rol = rolRepository.findById(CreateUsuarioRequest.getIdRol())
                .orElseThrow(() -> new Exception("Rol no encontrado con ID: " + CreateUsuarioRequest.getIdRol()));

        // Mapear el request a Modelo y establecer relaciones
        Usuario usuario = UsuarioMapper.createRequestToModel(CreateUsuarioRequest);
        usuario.setRol(rol);

        // ENCRIPTAR la password antes de guardar
        usuario.setPasswordHash(passwordEncoder.encode(CreateUsuarioRequest.getPasswordHash()));

        // Guardar en db
        usuario = usuarioRepository.save(usuario);

        // Convertir a Response y Retornar
        return UsuarioMapper.modelToCreateResponse(usuario);
    }

    // Metodo actualizar usuarilo
    public CreateUsuarioResponse updateUsuario(Integer idUsuario, CreateUsuarioRequest request) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + idUsuario));

        // Validar que el estado de Usuario no sea nulo
        if (request == null){
            throw new Exception("El Usuario no puede ser nulo");
        }

        // Validar que el nombre de Usuario no sea nulo
        if (request.getNombre() == null){
            throw new Exception("El nombre del Usuario no puede ser nulo");
        }

        // Validar que el apellido de Usuario no sea nulo
        if (request.getApellido() == null){
            throw new Exception("El apellido del Usuario no puede ser nulo");
        }

        // Validar que el email de Usuario no sea nulo
        if (request.getEmail() == null){
            throw new Exception("El email del Usuario no puede ser nulo");
        }

        // Validar que la contraseña no sea nula
        if (request.getPasswordHash() == null){
            throw new Exception("La contraseña no puede ser nula");
        }

        // Validar que la fecha no sea nula
//        if (request.getFechaCreacion() == null){
//            throw new Exception("La fecha no puede ser nula");
//        }

        // Validar ID de entidad relacionada
        if (request.getIdRol() == null || request.getIdRol() <= 0){
            throw new Exception("El ID del Rol es Invalido");
        }

        // Obtener entidad Relacionada
        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new Exception("Rol no encontrado con ID: " + request.getIdRol()));

        // Actualizar campos
        usuarioExistente.setNombre(request.getNombre());
        usuarioExistente.setApellido(request.getApellido());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        usuarioExistente.setFechaCreacion(request.getFechaCreacion() != null ? request.getFechaCreacion() : usuarioExistente.getFechaCreacion());
        usuarioExistente.setRol(rol);

        // Guardar en DB
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);

        // Retornar como DTO
        return UsuarioMapper.modelToCreateResponse(usuarioActualizado);
    }

    // Metodo de eliminar
    @Override
    public void deleteUsuario(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + idUsuario));
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}
