package com.proyecto.taller.repository;


import com.proyecto.taller.model.ImagenModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<ImagenModel, Long> {
    // Encuentra todas las imágenes asociadas a un producto por el ID del producto
    List<ImagenModel> findByProductoId(Long productoId);

}