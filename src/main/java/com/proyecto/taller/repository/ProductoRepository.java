package com.proyecto.taller.repository;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.taller.model.Categoria;
import com.proyecto.taller.model.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


}