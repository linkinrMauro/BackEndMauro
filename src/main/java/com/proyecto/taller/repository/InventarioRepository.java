package com.proyecto.taller.repository;

import com.proyecto.taller.model.Inventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
   // List<Inventario> findByProductoId(Long productoId);  // Buscar por producto
}