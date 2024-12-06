package com.proyecto.taller.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venta_id")
    private Long id;

    @Column(name = "fecha_venta", nullable = false)
    private Date fechaVenta;

    @Column(name = "cantidad_vendida", nullable = false)
    private Integer cantidadVendida;

    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta; // Precio de venta por unidad
    
	@Column(name = "estado")
	int estado;
/*
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
 */
    // Getters y Setters
}
