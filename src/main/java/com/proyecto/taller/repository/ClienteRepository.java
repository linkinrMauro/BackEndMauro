package com.proyecto.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.taller.model.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombre(String nombre);
}
