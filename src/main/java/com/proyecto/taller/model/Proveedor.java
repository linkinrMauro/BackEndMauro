package com.proyecto.taller.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
	int estado;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Producto> productos;  // Relación uno a muchos con productos

	
    
    
    
}
