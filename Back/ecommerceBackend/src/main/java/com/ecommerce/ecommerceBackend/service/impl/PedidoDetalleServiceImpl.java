package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.PedidoDetalleDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreatePedidoDetalleRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreatePedidoDetalleResponse;
import com.ecommerce.ecommerceBackend.mapper.PedidoDetalleMapper;
import com.ecommerce.ecommerceBackend.model.Pedido;
import com.ecommerce.ecommerceBackend.model.PedidoDetalle;
import com.ecommerce.ecommerceBackend.model.Producto;
import com.ecommerce.ecommerceBackend.repository.PedidoDetalleRepository;
import com.ecommerce.ecommerceBackend.repository.PedidoRepository;
import com.ecommerce.ecommerceBackend.repository.ProductoRepository;
import com.ecommerce.ecommerceBackend.service.PedidoDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    // Obtener Todo
    @Override
    public List<PedidoDetalle>getAllPedidosDetalles(){
        return pedidoDetalleRepository.findAll();
    }

    // Obtener Por ID
    @Override
    public PedidoDetalleDTO getPedidoDetalleById(Integer idDetalle){

        // Consultar en db por su ID
        PedidoDetalle pedidoDetalle = pedidoDetalleRepository.findById(idDetalle)
                .orElseThrow(() -> new RuntimeException("Detalle del pedido con ID: " + idDetalle + " no encontrado"));

        // Mapear hacia DTO el resultado que trae el modelo
        PedidoDetalleDTO pedidoDetalleDTO = PedidoDetalleMapper.modelToDTO(pedidoDetalle);

        // Retornar objeto mapeado a DTO
        return pedidoDetalleDTO;
    }

    // Crear pedido detalle en back
    @Override
    public CreatePedidoDetalleResponse createPedidoDetalleResponse(CreatePedidoDetalleRequest createPedidoDetalleRequest) throws Exception{

        // Validar que el detalle no sea nulo
        if(createPedidoDetalleRequest == null){
            throw new Exception("El detalle no puede ser nulo");
        }

        // Validar cantidad
        if(createPedidoDetalleRequest.getCantidad() == null || createPedidoDetalleRequest.getCantidad() <= 0){
            throw new Exception("La cantidad no puede ser nula o menor a 1");
        }

        // Validar Precio Unitario
        if(createPedidoDetalleRequest.getPrecioUnitario() == null || createPedidoDetalleRequest.getPrecioUnitario() <= 0){
            throw new Exception("El precio unitario no puede ser nulo");
        }

        // Validar IDs
        if(createPedidoDetalleRequest.getIdPedido() == null || createPedidoDetalleRequest.getIdPedido() <= 0){
            throw new Exception("El ID del pedido es Invalido");
        }

        if(createPedidoDetalleRequest.getIdProducto() == null || createPedidoDetalleRequest.getIdProducto() <= 0){
            throw new Exception("El ID del producto es Invalido");
        }

        // Cargar ID relacion
        Pedido pedido = pedidoRepository.findById(createPedidoDetalleRequest.getIdPedido())
                .orElseThrow(() -> new Exception("Pedido no encontrado con ID: " + createPedidoDetalleRequest.getIdPedido()));

        Producto producto = productoRepository.findById(createPedidoDetalleRequest.getIdProducto())
                .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + createPedidoDetalleRequest.getIdProducto()));

        // Mapear request a modelo
        PedidoDetalle pedidoDetalle = PedidoDetalleMapper.createRequestToModel(createPedidoDetalleRequest);

        // Setear IDs
        pedidoDetalle.setPedido(pedido);
        pedidoDetalle.setProducto(producto);

        // Persistir en db
        pedidoDetalle = pedidoDetalleRepository.save(pedidoDetalle);

        // Convertir a Response para retornar
        CreatePedidoDetalleResponse createPedidoDetalleResponse = PedidoDetalleMapper.modelToCreateResponse(pedidoDetalle);

        // Retornar
        return createPedidoDetalleResponse;
    }

    // Metodo Para Actualizar
    @Override
    public CreatePedidoDetalleResponse updatePedidoDetalle(Integer idDetalle, CreatePedidoDetalleRequest request) throws Exception{

        // Verificamos que exista el detalle
        PedidoDetalle pedidoDetalleExistente = pedidoDetalleRepository.findById(idDetalle)
                .orElseThrow(() -> new Exception("Detalle del pedido con ID: " + idDetalle + " no encontrado"));

        // Validaciones
        // Validar cantidad
        if(request.getCantidad() == null || request.getCantidad() <= 0){
            throw new Exception("La cantidad no puede ser nula o menor a 1");
        }

        // Validar Precio Unitario
        if(request.getPrecioUnitario() == null || request.getPrecioUnitario() <= 0){
            throw new Exception("El precio unitario no puede ser nulo");
        }

        // Validar IDs
        if(request.getIdPedido() == null || request.getIdPedido() <= 0){
            throw new Exception("El ID del pedido es Invalido");
        }

        if(request.getIdProducto() == null || request.getIdProducto() <= 0){
            throw new Exception("El ID del producto es Invalido");
        }

        // Actualizar datos
        pedidoDetalleExistente.setCantidad(request.getCantidad());
        pedidoDetalleExistente.setPrecioUnitario(request.getPrecioUnitario());
        pedidoDetalleExistente.setPedido(
                Pedido.builder().idPedido(request.getIdPedido()).build());
        pedidoDetalleExistente.setProducto(
                Producto.builder().idProducto(request.getIdProducto()).build());

        // Guardar Datos
        pedidoDetalleExistente = pedidoDetalleRepository.save(pedidoDetalleExistente);

        // Mapear y Retornar
        return PedidoDetalleMapper.modelToCreateResponse(pedidoDetalleExistente);
    }

    // Metodo Para Eliminar
    @Override
    public void deletePedidoDetalle(Integer idDetalle) throws Exception{

        // Verificamos que exista el detalle del pedido
        if(!pedidoRepository.existsById(idDetalle)){
            throw new Exception("Detalle del pedido con ID: " + idDetalle + " no encontrado");
        }

        // Eliminamos
        pedidoDetalleRepository.deleteById(idDetalle);
    }
}
