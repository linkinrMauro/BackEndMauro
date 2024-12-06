package com.proyecto.taller.repository;

import com.proyecto.taller.model.Categoria;
import com.proyecto.taller.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  
}
