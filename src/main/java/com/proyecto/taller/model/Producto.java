package com.proyecto.taller.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "foto")
    private String foto;


    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra;  // Precio al que se compró el producto

    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;  // Precio al que se venderá el producto

    @Column(name = "peso", nullable = false)
    private Double peso;  // Peso del producto (kilos o gramos)

    @Column(name = "estado")
	int estado;
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;  // Fecha en que se ingresó el producto

    @Column(name = "fecha_caducidad", nullable = false)
    private LocalDate fechaCaducidad;  // Fecha de caducidad del producto

    @ManyToOne(optional = false) // Asegúrate de que no sea opcional
    @JoinColumn(name = "proveedor_id", nullable = false) // Define el nombre de la columna y que no puede ser nula
    private Proveedor proveedor; // Relación muchos a uno con proveedor

 
     // Relación muchos a uno con inventario, obligatoria
     @ManyToOne(optional = false) // Esta relación es obligatoria para el producto
     @JoinColumn(name = "inventario_id", nullable = false)
     private Inventario inventario; // Un producto debe tener un inventario    

     @ManyToOne(optional = false)
     @JoinColumn(name = "categoria_id", nullable = false)
     private Categoria categoria;


    
    


      
      
      
}