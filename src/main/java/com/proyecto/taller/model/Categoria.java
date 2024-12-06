package com.proyecto.taller.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

	@Column(name = "estado")
	int estado;
	
	 @Column(name = "descripcion")
	  private String Descripcion;

	  @Column(name = "foto")
	  private String foto;

	  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	    private List<Producto> productos;
	  
	  

	  
	public Categoria(Long id, String nombre, int estado, String descripcion, List<Producto> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		Descripcion = descripcion;
		this.productos = productos;
	}

	public Categoria() {
		super();
	}

    

    
}
