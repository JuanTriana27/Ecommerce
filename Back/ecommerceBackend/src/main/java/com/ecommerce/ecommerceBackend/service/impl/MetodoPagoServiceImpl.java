package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.MetodoPagoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateMetodoPagoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateMetodoPagoResponse;
import com.ecommerce.ecommerceBackend.mapper.MetodoPagoMapper;
import com.ecommerce.ecommerceBackend.model.MetodoPago;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.repository.MetodoPagoRespository;
import com.ecommerce.ecommerceBackend.repository.UsuarioRepository;
import com.ecommerce.ecommerceBackend.service.MetodoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRespository metodoPagoRespository;
    private final UsuarioRepository usuarioRepository;

    // Obtener todas las direcciones
    @Override
    public List<MetodoPago> getAllMetodosPago(){
        return metodoPagoRespository.findAll();
    }

    // Obtener Metodos Pago por ID
    @Override
    public MetodoPagoDTO getMetodoPagoById(Integer idMetodoPago){

        // Consultar en db el metodo pago por su id
        MetodoPago metodoPago = metodoPagoRespository.findById(idMetodoPago)
                .orElseThrow(() -> new RuntimeException("Metodo Pago no encontrado con ID: " + idMetodoPago));

        // Mapear hacia DTO el resultado que trae el modelo
        MetodoPagoDTO metodoPagoDTO = MetodoPagoMapper.modelToDTO(metodoPago);

        // Retornar el objeto mapeado a DTO
        return metodoPagoDTO;
    }

    // Crear Metodo de pago
    @Override
    public CreateMetodoPagoResponse createMetodoPagoResponse (CreateMetodoPagoRequest createMetodoPagoRequest) throws Exception {

        // Validar que el metodo pago no sea nulo
        if (createMetodoPagoRequest == null){
            throw new Exception("El metodo pago no puede ser nulo");
        }

        // Validar el tipo de pago
        if (createMetodoPagoRequest.getTipo() == null){
            throw new Exception("El tipo de pago no puede ser nulo");
        }

        // Validar numero tarjeta
        if (createMetodoPagoRequest.getNumeroTarjeta() == null){
            throw new Exception("El numero de tarjeta no puede ser nulo");
        }

        // Validar nombre titular
        if (createMetodoPagoRequest.getNombreTitular() == null){
            throw new Exception("El nombre del titular no puede ser nulo");
        }

        // Validar Fecha expiracion
        if (createMetodoPagoRequest.getFechaExpiracion() == null){
            throw new Exception("La fecha de expiracion no puede ser nula");
        }

        // Validar el id del usuario
        if (createMetodoPagoRequest.getIdUsuario() == null || createMetodoPagoRequest.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Cargar el usuario
        Usuario usuario = usuarioRepository.findById(createMetodoPagoRequest.getIdUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + createMetodoPagoRequest.getIdUsuario()));

        // Mapear el request a modelo
        MetodoPago metodoPago = MetodoPagoMapper.createRequestToModel(createMetodoPagoRequest);

        // Setear el usuario en el metodo pago
        metodoPago.setUsuario(usuario);

        // Persistir el modelo en db
        metodoPago = metodoPagoRespository.save(metodoPago);

        // Convertir a response para retornar
        CreateMetodoPagoResponse createMetodoPagoResponse = MetodoPagoMapper.modelToCreateResponse(metodoPago);

        // Retornar el response persistido como solicita el metodo
        return createMetodoPagoResponse;
    }

    // Metodo para actualizar el metodo pago existente
    @Override
    public CreateMetodoPagoResponse updateMetodoPago(Integer idMetodoPago, CreateMetodoPagoRequest request) throws Exception {

        // Verificamos que exista el metodo pago
        MetodoPago metodoPagoExistente = metodoPagoRespository.findById(idMetodoPago)
                .orElseThrow(() -> new Exception("Metodo Pago no encontrado con ID: " + idMetodoPago));

        // Validar el tipo de pago
        if (request.getTipo() == null){
            throw new Exception("El tipo de pago no puede ser nulo");
        }

        // Validar numero de tarjeta
        if (request.getNumeroTarjeta() == null){
            throw new Exception("El numero de tarjeta no puede ser nulo");
        }

        // Validar nombre del titular
        if (request.getNombreTitular() == null){
            throw new Exception("El nombre del titular no puede ser nulo");
        }

        // Validar fecha expiracion
        if (request.getFechaExpiracion() == null){
            throw new Exception("La fecha de expiracion no puede ser nula");
        }

        // Validar el id del Usuario
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Actualizar datos del metodo de pago con nuevos datos
        metodoPagoExistente.setTipo(request.getTipo());
        metodoPagoExistente.setNumeroTarjeta(request.getNumeroTarjeta());
        metodoPagoExistente.setNombreTitular(request.getNombreTitular());
        metodoPagoExistente.setFechaExpiracion(request.getFechaExpiracion());
        metodoPagoExistente.setUsuario(
                Usuario.builder().idUsuario(request.getIdUsuario()).build()
        );

        // Guardar el metodo actualizado
        metodoPagoExistente = metodoPagoRespository.save(metodoPagoExistente);

        // Mapear y retornar
        return MetodoPagoMapper.modelToCreateResponse(metodoPagoExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteMetodoPago(Integer idMetodoPago) throws Exception{

        // Verificamos que exista el metodo de pago
        if(!metodoPagoRespository.existsById(idMetodoPago)){
            throw new Exception("Metodo Pago no encontrado con ID: " + idMetodoPago);
        }

        // Eliminamos
        metodoPagoRespository.deleteById(idMetodoPago);
    }
}
