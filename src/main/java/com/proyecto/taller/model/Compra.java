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
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_id")
    private Long id;

    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;

    @Column(name = "cantidad_comprada", nullable = false)
    private Integer cantidadComprada;

    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra; // Precio de compra por unidad
/*
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;
*/
    // Getters y Setters
}

