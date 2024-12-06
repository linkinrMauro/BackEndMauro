package com.proyecto.taller.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "nombre", nullable = false)
    private String nombre;


    @Column(name = "cantidad", nullable = false)
    private Double cantidad;  // Cantidad de producto en este lote
    
	@Column(name = "estado")
	int estado;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;  // Fecha en que se ingresó el lote al inventario

  // Relación uno a muchos con productos, opcional
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL)
    private List<Producto> productos; // Lista de productos, pero no obligatoria para crear un inventario




}
