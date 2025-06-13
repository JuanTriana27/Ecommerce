package com.ecommerce.ecommerceBackend.service.impl;

import com.ecommerce.ecommerceBackend.dto.CategoriaDTO;
import com.ecommerce.ecommerceBackend.dto.request.CreateCategoriaRequest;
import com.ecommerce.ecommerceBackend.dto.response.CreateCategoriaResponse;
import com.ecommerce.ecommerceBackend.mapper.CategoriaMapper;
import com.ecommerce.ecommerceBackend.model.Categoria;
import com.ecommerce.ecommerceBackend.repository.CategoriaRepository;
import com.ecommerce.ecommerceBackend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Obtener Todos los Roles
    @Override
    public List<Categoria> getAllCategorias(){
        return categoriaRepository.findAll();
    }

    // Obtener Categoria por ID
    @Override
    public CategoriaDTO getCategoriaById(Integer idCategoria){

        // Consultar en db la categoria por ID
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + idCategoria));

        // Mapear hacia DTO el resultado que trae el modelo
        CategoriaDTO categoriaDTO = CategoriaMapper.modelToDTO(categoria);

        // Retornar el objeto mapeado a DTO
        return categoriaDTO;
    }

    // Crear Categoria en el back
    @Override
    public CreateCategoriaResponse createCategoriaResponse (CreateCategoriaRequest createCategoriaRequest) throws Exception{

        // Validar que la categoria no sea nula
        if (createCategoriaRequest == null){
            throw new Exception("La categoria no puede ser nula");
        }

        // Validar que el nombre no sea nulo
        if (createCategoriaRequest.getNombre() == null){
            throw new Exception("El nombre de la categoria no puede ser nulo");
        }

        // Validar que la descripcion no sea nula
        if (createCategoriaRequest.getDescripcion() == null){
            throw new Exception("La descripcion de la categoria no puede ser nula");
        }

        // Mapear el request a Modelo
        Categoria categoria = CategoriaMapper.createRequestToModel(createCategoriaRequest);

        // Persistir el modelo en db
        categoria = categoriaRepository.save(categoria);

        // Convertir a Response para retornar
        CreateCategoriaResponse createCategoriaResponse = CategoriaMapper.modelToCreateResponse(categoria);

        // Retornamos el responsepersistido commo solicita el metodo
        return createCategoriaResponse;
    }

    // Metodo para actualizar categoria
    @Override
    public CreateCategoriaResponse updateCategoria(Integer idCategoria, CreateCategoriaRequest request) throws Exception {

        // Verificamos que exista la categoria
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new Exception("Categoria no encontrada con ID: " + idCategoria));

        // Validar que el nombre no sea nulo
        if (request.getNombre() == null) {
            throw new Exception("El nombre de la categoria no puede ser nulo");
        }

        // Validar que la descripcion no sea nula
        if (request.getDescripcion() == null) {
            throw new Exception("La descripcion de la categoria no puede ser nula");
        }

        // Actualizar datos de la categoria con nuevos valores
        categoriaExistente.setNombre(request.getNombre());
        categoriaExistente.setDescripcion(request.getDescripcion());

        // Guardar la categoria actualizada
        categoriaExistente = categoriaRepository.save(categoriaExistente);

        // Mapear y retornar
        return CategoriaMapper.modelToCreateResponse(categoriaExistente);
    }

    // Metodo para eliminar
    @Override
    public void deleteCategoria(Integer idCategoria) throws Exception{

        // Verificamos que exista
        if (!categoriaRepository.existsById(idCategoria)){
            throw new Exception("Categoria no encontrada con ID: " + idCategoria);
        }

        // Eliminamos
        categoriaRepository.deleteById(idCategoria);
    }
}
