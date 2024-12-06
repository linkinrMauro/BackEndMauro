package com.proyecto.taller.service;

import com.proyecto.taller.model.Categoria;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoriaService {
    Categoria crearCategoria(Categoria categoria, MultipartFile imagen);
    Categoria editarCategoria(Long id, Categoria categoria, MultipartFile imagen);
    void eliminarCategoria(Long id);
    List<Categoria> listarCategorias();
    Categoria obtenerCategoriaPorId(Long id);
    ///mostrar imagen 
    Resource cargarImagen(String nombreImagen) throws IOException;
}