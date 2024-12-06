package com.proyecto.taller.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "usuario_model")
public class UsuariosModel {
	@Id
	@Column(name = "login")
	String login;

	@Column(name = "passwd")
	String passwd;

	@Column(name = "estado")
	int estado;

	@Column(name="token")
	String token;
	
 // Relación uno a uno con ImagenModel
 @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
 @JsonIgnore
 private ImagenModel imagen;

    // Relación uno a uno con Cliente
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    // Relación uno a uno con Empleado
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Empleado empleado;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ImagenModel getImagen() {
		return imagen;
	}

	public void setImagen(ImagenModel imagen) {
		this.imagen = imagen;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public UsuariosModel(String login, String passwd, int estado, String token, ImagenModel imagen, Cliente cliente,
			Empleado empleado) {
		super();
		this.login = login;
		this.passwd = passwd;
		this.estado = estado;
		this.token = token;
		this.imagen = imagen;
		this.cliente = cliente;
		this.empleado = empleado;
	}

	public UsuariosModel() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
