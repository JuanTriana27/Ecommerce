package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.CarritoItemDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCarritoItemRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCarritoItemResponse;
import com.ecommerce.ecommerceBackend.mapper.CarritoItemMapper;
import com.ecommerce.ecommerceBackend.model.Carrito;
import com.ecommerce.ecommerceBackend.model.CarritoItem;
import com.ecommerce.ecommerceBackend.model.Producto;
import com.ecommerce.ecommerceBackend.repository.CarritoItemRepository;
import com.ecommerce.ecommerceBackend.repository.CarritoRepository;
import com.ecommerce.ecommerceBackend.repository.ProductoRepository;
import com.ecommerce.ecommerceBackend.service.CarritoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoItemServiceImpl implements CarritoItemService {

    private final CarritoItemRepository carritoItemRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    // Obtener todos los items
    @Override
    public List<CarritoItem>getAllCarritoItems(){
        return carritoItemRepository.findAll();
    }

    // Obtener por id
    @Override
    public CarritoItemDTO getCarritoItemById(Integer idCarritoItem){

        // Consultar en db carrito por su id
        CarritoItem carritoItem = carritoItemRepository.findById(idCarritoItem)
                .orElseThrow(() -> new RuntimeException("Items del carrito con ID: " + idCarritoItem + " no encontrados"));

        // Mapear hacia DTO el resultado que trae el modelo
        CarritoItemDTO carritoItemDTO = CarritoItemMapper.modelToDTO(carritoItem);

        // Retornar el objeto mapeado a DTO
        return carritoItemDTO;
    }

    // Crear items en el back
    @Override
    public CreateCarritoItemResponse createCarritoItemResponse (CreateCarritoItemRequest createCarritoItemRequest) throws Exception{

        // Validar que los items del carrito no sean nulos
        if (createCarritoItemRequest == null){
            throw new Exception("Los items del carrito no pueden ser nulos");
        }

        // Validar cantidad
        if (createCarritoItemRequest.getCantidad() == null || createCarritoItemRequest.getCantidad() <= 0){
            throw new Exception("La cantidad no puede ser nula o menor a 1");
        }

        // Validar id de carrito y de producto
        if (createCarritoItemRequest.getIdCarrito() == null || createCarritoItemRequest.getIdCarrito() <= 0){
            throw new Exception("El ID del carrito es Invalido");
        }

        if (createCarritoItemRequest.getIdProducto() == null || createCarritoItemRequest.getIdProducto() <= 0){
            throw new Exception("El ID del producto es Invalido");
        }

        // Cargar carrito y producto
        Carrito carrito = carritoRepository.findById(createCarritoItemRequest.getIdCarrito())
                .orElseThrow(() -> new Exception("Carrito no encontrado con ID: " + createCarritoItemRequest.getIdCarrito()));

        Producto producto = productoRepository.findById(createCarritoItemRequest.getIdProducto())
                .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + createCarritoItemRequest.getIdProducto()));

        // Mapear request a modelo
        CarritoItem carritoItem = CarritoItemMapper.createRequestToModel(createCarritoItemRequest);

        // Setear carrito y producto
        carritoItem.setCarrito(carrito);
        carritoItem.setProducto(producto);

        // persistir en db
        carritoItem = carritoItemRepository.save(carritoItem);

        // Convertir a response para retornar
        CreateCarritoItemResponse createCarritoItemResponse = CarritoItemMapper.modelToCreateResponse(carritoItem);

        // Retornar el response persistido como solicita el metodo
        return createCarritoItemResponse;
    }

    // Metodo para actualizar
    @Override
    public CreateCarritoItemResponse updateCarritoItem (Integer idCarritoItem, CreateCarritoItemRequest request) throws Exception{

        // Verificamos que exista el carritoitems
        CarritoItem carritoItemExistente = carritoItemRepository.findById(idCarritoItem)
                .orElseThrow(() -> new Exception("Items del carrito con ID: " + idCarritoItem + " no encontrados"));

        // Validar los id de carrito y producto
        if (request.getIdCarrito() == null || request.getIdCarrito() <= 0){
            throw new Exception("El ID del carrito es Invalido");
        }

        if (request.getIdProducto() == null || request.getIdProducto() <= 0){
            throw new Exception("El ID del producto es Invalido");
        }

        // Actualizar datos del carrito
        carritoItemExistente.setCantidad(request.getCantidad());

        carritoItemExistente.setCarrito(
                Carrito.builder().idCarrito(request.getIdCarrito()).build()
        );

        carritoItemExistente.setProducto(
                Producto.builder().idProducto(request.getIdProducto()).build()
        );

        // Guardar carritoitem actualizado
        carritoItemExistente = carritoItemRepository.save(carritoItemExistente);

        // Mapear y retornar
        return CarritoItemMapper.modelToCreateResponse(carritoItemExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteCarritoItem(Integer idCarritoItem) throws Exception{

        // Verificamos que exista carritoitems
        if(!carritoItemRepository.existsById(idCarritoItem)){
            throw new Exception("Items del carrito con ID: " + idCarritoItem + " no encontrados");
        }

        // Eliminamos
        carritoItemRepository.deleteById(idCarritoItem);
    }
}
