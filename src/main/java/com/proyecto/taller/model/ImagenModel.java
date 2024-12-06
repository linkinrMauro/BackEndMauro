package com.proyecto.taller.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes")
public class ImagenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruta")
    private String ruta;

    @OneToOne
    @JsonBackReference 
    @JoinColumn(name = "login_usuario")  // Se usa para asociar el usuario con la imagen
    private UsuariosModel usuario;
   
    @ManyToOne
    @JoinColumn(name = "producto_id")  // Relación muchos a uno con producto
    private Producto producto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public UsuariosModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuariosModel usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


    
}
