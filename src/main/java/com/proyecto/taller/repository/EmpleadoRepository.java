package com.proyecto.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.taller.model.Empleado;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByNombre(String nombre);
}
