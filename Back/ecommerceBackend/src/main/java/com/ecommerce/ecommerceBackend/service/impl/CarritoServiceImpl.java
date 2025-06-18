package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.CarritoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoResponse;
import com.ecommerce.ecommerceBackend.mapper.CarritoMapper;
import com.ecommerce.ecommerceBackend.model.Carrito;
import com.ecommerce.ecommerceBackend.model.Usuario;
import com.ecommerce.ecommerceBackend.repository.CarritoRepository;
import com.ecommerce.ecommerceBackend.repository.UsuarioRepository;
import com.ecommerce.ecommerceBackend.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;

    // Obtener todos los carritos
    @Override
    public List<Carrito> getAllCarritos(){
        return carritoRepository.findAll();
    }

    // Obtener por id
    @Override
    public CarritoDTO getCarritoById(Integer idCarrito){

        // Consular en db el carrito por su id
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        // Mapear hacia DTO el resultado que trae el modelo
        CarritoDTO carritoDTO = CarritoMapper.modelToDTO(carrito);

        // Retornar el objeto mapeado a DTO
        return carritoDTO;
    }

    // Crear Carrito en el Back
    @Override
    public CreateCarritoResponse createCarritoResponse (CreateCarritoRequest createCarritoRequest) throws Exception{

        // Validar que el carrito no sea nulo
        if (createCarritoRequest == null) {
            throw new Exception("El carrito no puede ser nulo");
        }

        // Validar el id del usuario
        if (createCarritoRequest.getIdUsuario() == null || createCarritoRequest.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Cargar el usuario
        Usuario usuario = usuarioRepository.findById(createCarritoRequest.getIdUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + createCarritoRequest.getIdUsuario()));

        // Mapear el request a modelo
        Carrito carrito = CarritoMapper.createRequestToModel(createCarritoRequest);

        // Setear el usuario en el carrito
        carrito.setUsuario(usuario);

        // Persistir el modelo en db
        carrito = carritoRepository.save(carrito);

        // Convertir a response para retornar
        CreateCarritoResponse createCarritoResponse = CarritoMapper.modelToCreateResponse(carrito);

        // Retornar el response persistido como solicita el metodo
        return createCarritoResponse;
    }

    // Metodo para actualizar carrito
    @Override
    public CreateCarritoResponse updateCarrito (Integer idCarrito, CreateCarritoRequest request) throws Exception {

        // Verificamos que exista el carrito
        Carrito carritoExistente = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new Exception("Carrito no encontrado con ID: " + idCarrito));

        // Validar el id del usuario
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0){
            throw new Exception("El ID del usuario es Invalido");
        }

        // Actualizar datos del carrito
        carritoExistente.setUsuario(
                Usuario.builder().idUsuario(request.getIdUsuario()).build()
        );

        // Guardar carrito actualizado
        carritoExistente = carritoRepository.save(carritoExistente);

        // Mapear y retornar
        return CarritoMapper.modelToCreateResponse(carritoExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteCarrito(Integer idCarrito) throws Exception{

        // Verificamos que exista el carrito
        if(!carritoRepository.existsById(idCarrito)){
            throw new Exception("Carrito no encontrado con ID: " + idCarrito);
        }

        // Eliminamos
        carritoRepository.deleteById(idCarrito);
    }
}
