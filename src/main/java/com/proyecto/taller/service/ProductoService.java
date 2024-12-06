package com.proyecto.taller.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.taller.model.Producto;

public interface ProductoService {
  
    Producto crearProducto(Producto producto, MultipartFile file) throws Exception;


    Producto actualizarProducto(Long id, Producto producto, MultipartFile file) throws Exception;

   
    void eliminarProducto(Long id) throws Exception;


    Producto obtenerProductoPorId(Long id);

 
    List<Producto> listarProductos();

  
    Resource cargarImagen(String nombreImagen) throws IOException;
}