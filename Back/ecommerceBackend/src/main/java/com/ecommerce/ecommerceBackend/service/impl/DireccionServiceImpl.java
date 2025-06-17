package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.DireccionDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateDireccionRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateDireccionResponse;
import com.ecommerce.ecommerceBackend.mapper.DireccionMapper;
import com.ecommerce.ecommerceBackend.model.Direccion;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.repository.DireccionRepository;
import com.ecommerce.ecommerceBackend.service.DireccionService;
import com.ecommerce.ecommerceBackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;


    // Obtener todas las direcciones
    @Override
    public List<Direccion> getAllDirecciones(){
        return direccionRepository.findAll();
    }

    // Obtener Direcciones por ID
    @Override
    public DireccionDTO getDireccionById(Integer idDireccion){

        // Consultar en db la direccion por su id
        Direccion direccion = direccionRepository.findById(idDireccion)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con ID: " + idDireccion));

        // Mapear hacia DTO el resultado que trae el modelo
        DireccionDTO direccionDTO = DireccionMapper.modelToDTO(direccion);

        // Retornar el objeto mapeado a DTO
        return direccionDTO;
    }

    // Crear Direccion en el Back
    @Override
    public CreateDireccionResponse createDireccionResponse (CreateDireccionRequest createDireccionRequest) throws Exception {

        // Validar que la direccion no sea nula
        if (createDireccionRequest == null){
            throw new Exception("La direccion no puede ser nula");
        }

        // Validar que el nombre destinatario no sea nulo
        if (createDireccionRequest.getNombreDestinatario() == null) {
            throw new Exception("El nombre destinatario no puede ser nulo");
        }

        // Validar que la direccion no sea nula
        if (createDireccionRequest.getDireccion() == null) {
            throw new Exception("La direccion no puede ser nula");
        }

        // Validar que la ciudad no sea nula
        if (createDireccionRequest.getCiudad() == null) {
            throw new Exception("La ciudad no puede ser nula");
        }

        // Validar que el departamento no sea nulo
        if (createDireccionRequest.getDepartamento() == null) {
            throw new Exception("El departamento no puede ser nulo");
        }

        // Validar que el pais no sea nulo
        if (createDireccionRequest.getPais() == null) {
            throw new Exception("El pais no puede ser nulo");
        }

        // Validar que el codigo postal no sea nulo
        if (createDireccionRequest.getCodigoPostal() == null) {
            throw new Exception("El codigo postal no puede ser nulo");
        }

        // Validar el id del usuario
        if (createDireccionRequest.getIdUsuario() == null || createDireccionRequest.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Cargar el usuario
        Usuario usuario = usuarioRepository.findById(createDireccionRequest.getIdUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + createDireccionRequest.getIdUsuario()));

        // Mapear el request a modelo
        Direccion direccion = DireccionMapper.createRequestToModel(createDireccionRequest);

        // Setear el usuario en la direccion
        direccion.setUsuario(usuario);

        // Persistir el modelo en db
        direccion = direccionRepository.save(direccion);

        // Convertir a Response para retornar
        CreateDireccionResponse createDireccionResponse = DireccionMapper.modelToCreateResponse(direccion);

        // Retornar el response persistido como solicita el metodo
        return createDireccionResponse;
    }

    // Metodo para actualizar la direccion existente
    @Override
    public CreateDireccionResponse updateDireccion(Integer idDireccion, CreateDireccionRequest request) throws Exception {

        // Verificamos que exista la direccion
        Direccion direccionExistente = direccionRepository.findById(idDireccion)
                .orElseThrow(() -> new Exception("Direccion no encontrada con ID: " + idDireccion));

        // Validar que el nombre destinatario no sea nulo
        if (request.getNombreDestinatario() == null) {
            throw new Exception("El nombre destinatario no puede ser nulo");
        }

        // Validar que la direccion no sea nula
        if (request.getDireccion() == null) {
            throw new Exception("La direccion no puede ser nula");
        }

        // Validar que la ciudad no sea nula
        if (request.getCiudad() == null) {
            throw new Exception("La ciudad no puede ser nula");
        }

        // Validar que el departamento no sea nulo
        if (request.getDepartamento() == null) {
            throw new Exception("El departamento no puede ser nulo");
        }

        // Validar que el pais no sea nulo
        if (request.getPais() == null) {
            throw new Exception("El pais no puede ser nulo");
        }

        // Validar que el codigo postal no sea nulo
        if (request.getCodigoPostal() == null) {
            throw new Exception("El codigo postal no puede ser nulo");
        }

        // Validar el id del usuario
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Actulizar datos de la direccion con los nuevos datos
        direccionExistente.setNombreDestinatario(request.getNombreDestinatario());
        direccionExistente.setDireccion(request.getDireccion());
        direccionExistente.setCiudad(request.getCiudad());
        direccionExistente.setDepartamento(request.getDepartamento());
        direccionExistente.setPais(request.getPais());
        direccionExistente.setCodigoPostal(request.getCodigoPostal());
        direccionExistente.setUsuario(
                Usuario.builder().idUsuario(request.getIdUsuario()).build()
        );

        // Guardar la direccion actualizada
        direccionExistente = direccionRepository.save(direccionExistente);

        // Mapear y retornar
        return DireccionMapper.modelToCreateResponse(direccionExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteDireccion(Integer idDireccion) throws Exception {

        // Verificamos que exista la direccion
        if(!direccionRepository.existsById(idDireccion)){
            throw new Exception("Direccion no encontrada con ID: " + idDireccion);
        }

        // Eliminamos
        direccionRepository.deleteById(idDireccion);
    }
}