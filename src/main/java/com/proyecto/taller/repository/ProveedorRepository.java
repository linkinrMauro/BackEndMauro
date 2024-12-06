package com.proyecto.taller.repository;

import com.proyecto.taller.model.Proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombreContaining(String nombre);  // Buscar por nombre
}