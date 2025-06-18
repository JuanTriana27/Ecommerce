package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.PedidoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoResponse;
import com.ecommerce.ecommerceBackend.mapper.PedidoMapper;
import com.ecommerce.ecommerceBackend.model.Direccion;
import com.ecommerce.ecommerceBackend.model.Pedido;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.repository.DireccionRepository;
import com.ecommerce.ecommerceBackend.repository.PedidoRepository;
import com.ecommerce.ecommerceBackend.repository.UsuarioRepository;
import com.ecommerce.ecommerceBackend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DireccionRepository direccionRepository;

    // Obtener todos los pedidos
    @Override
    public List<Pedido>getAllPedidos(){
        return pedidoRepository.findAll();
    }

    // Obtener por id
    public PedidoDTO getPedidoById (Integer idPedido){

        // Consular en db pedido por su id
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));

        // Mapear hacia DTO el resultado que trae el modelo
        PedidoDTO pedidoDTO = PedidoMapper.modelToDTO(pedido);

        // Retornar objeto mapeado a DTO
        return pedidoDTO;
    }

    // Crear pedido en back
    @Override
    public CreatePedidoResponse createPedidoResponse (CreatePedidoRequest createPedidoRequest) throws Exception {

        // Validar que el pedido no sea nulo
        if(createPedidoRequest == null){
            throw new Exception("El pedido no puede ser nulo");
        }

        // Validar Fecha
        if(createPedidoRequest.getFecha() == null){
            throw new Exception("La fecha no puede ser nula");
        }

        // Validar Estado
        if(createPedidoRequest.getEstado() == null){
            throw new Exception("El estado no puede ser nulo");
        }

        // Validar Total
        if(createPedidoRequest.getTotal() == null){
            throw new Exception("El total no puede ser nulo");
        }

        // Validar id de usuario y direccion
        if(createPedidoRequest.getIdUsuario() == null || createPedidoRequest.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        if(createPedidoRequest.getIdDireccion() == null || createPedidoRequest.getIdDireccion() <= 0){
            throw new Exception("El ID de la direccion es Invalido");
        }

        // Cargar usuario y direccion
        Usuario usuario = usuarioRepository.findById(createPedidoRequest.getIdUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + createPedidoRequest.getIdUsuario()));

        Direccion direccion = direccionRepository.findById(createPedidoRequest.getIdDireccion())
                .orElseThrow(() -> new Exception("Direccion no encontrada con ID: " + createPedidoRequest.getIdDireccion()));

        // Mapear request a modelo
        Pedido pedido = PedidoMapper.createRequestToModel(createPedidoRequest);

        // Setear usuario y direccion
        pedido.setUsuario(usuario);
        pedido.setDireccion(direccion);

        // Persis en db
        pedido = pedidoRepository.save(pedido);

        // Convertir a response para retornar
        CreatePedidoResponse createPedidoResponse = PedidoMapper.modelToCreateResponse(pedido);

        // Retornar el response persistido como solicita el modelo
        return createPedidoResponse;
    }

    // Metodo para Actualizar
    @Override
    public CreatePedidoResponse updatePedido(Integer idPedido, CreatePedidoRequest request) throws Exception{

        // Verficamos que exista el pedido
        Pedido pedidoExistente = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new Exception("Pedido no encontrado con ID: " + idPedido));

        // Validaciones
        if(request.getFecha() == null){
            throw new Exception("La fecha no puede ser nula");
        }

        if(request.getEstado() == null){
            throw new Exception("El estado no puede ser nulo");
        }

        if(request.getTotal() == null){
            throw new Exception("El total no puede ser nulo");
        }

        // Validar los id de usuario y direccion
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        if (request.getIdDireccion() == null || request.getIdDireccion() <= 0){
            throw new Exception("El ID de la direccion es Invalido");
        }

        // Actualizar datos del pedido
        pedidoExistente.setFecha(request.getFecha());
        pedidoExistente.setEstado(request.getEstado());
        pedidoExistente.setTotal(request.getTotal());
        pedidoExistente.setUsuario(
                Usuario.builder().idUsuario(request.getIdUsuario()).build());
        pedidoExistente.setDireccion(
                Direccion.builder().idDireccion(request.getIdDireccion()).build());

        // Guardar datos
        pedidoExistente = pedidoRepository.save(pedidoExistente);

        // Mapear y retornar
        return PedidoMapper.modelToCreateResponse(pedidoExistente);
    }

    // Metodo para eliminar
    @Override
    public void deletePedido(Integer idPedido) throws Exception{

        // Verificamos que exista el pedido
        if(!pedidoRepository.existsById(idPedido)){
            throw new Exception("Pedido no encontrado con ID: " + idPedido);
        }

        // Eliminamos
        pedidoRepository.deleteById(idPedido);
    }
}
