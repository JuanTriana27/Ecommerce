package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.ProductoDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateProductoRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateProductoResponse;
import com.ecommerce.ecommerceBackend.mapper.ProductoMapper;
import com.ecommerce.ecommerceBackend.model.Categoria;
import com.ecommerce.ecommerceBackend.model.Producto;
import com.ecommerce.ecommerceBackend.repository.ProductoRepository;
import com.ecommerce.ecommerceBackend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    // Obtener Todos los Productos
    @Override
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    // Obtener Productos por ID
    @Override
    public ProductoDTO getProductoById(Integer idProducto){

        // Consultar en db el producto por su id
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));

        // Mapear hacia DTO el resultado que trae el modelo
        ProductoDTO productoDTO = ProductoMapper.modelToDTO(producto);

        // Retornar el objeto mapeado a DTO
        return productoDTO;
    }

    // Crear Producto en el Back
    @Override
    public CreateProductoResponse createProductoResponse (CreateProductoRequest createProductoRequest) throws Exception{

        // Validar que el producto no sea nulo
        if (createProductoRequest == null){
            throw new Exception("El producto no puede ser nulo");
        }

        // Validar que el nombre no sea nulo
        if (createProductoRequest.getNombre() == null){
            throw new Exception("El nombre del producto no puede ser nulo");
        }

        // Validar que la descripcion no sea nula
        if (createProductoRequest.getDescripcion() == null){
            throw new Exception("La descripcion del producto no puede ser nula");
        }

        // Validar que el precio no sea nulo
        if (createProductoRequest.getPrecio() == null){
            throw new Exception("El precio del producto no puede ser nulo");
        }

        // Validar que el stock no sea nulo
        if (createProductoRequest.getStock() == null){
            throw new Exception("El stock del producto no puede ser nulo");
        }

        // Validar el id de la categoria
        if (createProductoRequest.getIdCategoria() == null || createProductoRequest.getIdCategoria() <= 0){
            throw new Exception("El ID de la categoria es Invalido");
        }

        // Mapear el request a modelo
        Producto producto = ProductoMapper.createRequestToModel(createProductoRequest);

        // Persistir el modelo en db
        producto = productoRepository.save(producto);

        // Convertir a Response para retornar
        CreateProductoResponse createProductoResponse = ProductoMapper.modelToCreateResponse(producto);

        // Retornar el response persistido como solicita el metodo
        return createProductoResponse;
    }

    // Metodo para actualizar el producto existente
    @Override
    public CreateProductoResponse updateProducto(Integer idProducto, CreateProductoRequest request) throws Exception {

        // Verificamos que exista el producto
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + idProducto));

        // Validar que el nombre no sea nulo
        if (request.getNombre() == null) {
            throw new Exception("El nombre del producto no puede ser nulo");
        }

        // Validar que la descripcion no sea nula
        if (request.getDescripcion() == null) {
            throw new Exception("La descripcion del producto no puede ser nula");
        }

        // Validar que el precio no sea nulo
        if (request.getPrecio() == null) {
            throw new Exception("El precio del producto no puede ser nulo");
        }

        // Validar que el stock no sea nulo
        if (request.getStock() == null) {
            throw new Exception("El stock del producto no puede ser nulo");
        }

        // Validar el id de la categoria
        if (request.getIdCategoria() == null || request.getIdCategoria() <= 0) {
            throw new Exception("El ID de la categoria es Invalido");
        }

        // Actualizar datos del producto con los nuevos datos
        productoExistente.setNombre(request.getNombre());
        productoExistente.setDescripcion(request.getDescripcion());
        productoExistente.setPrecio(request.getPrecio());
        productoExistente.setStock(request.getStock());
        productoExistente.setCategoria(
                Categoria.builder().idCategoria(request.getIdCategoria()).build()
        );

        // Guardar el producto actualizado
        productoExistente = productoRepository.save(productoExistente);

        // Mapear y retornar
        return ProductoMapper.modelToCreateResponse(productoExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteProducto(Integer idProducto) throws Exception{

        // Verificamos que exista el producto
        if(!productoRepository.existsById(idProducto)){
            throw new Exception("Producto no encontrado con ID: " + idProducto);
        }

        // Eliminamos
        productoRepository.deleteById(idProducto);
    }
}