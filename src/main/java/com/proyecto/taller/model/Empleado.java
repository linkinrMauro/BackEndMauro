package com.proyecto.taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleado_model")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "direccion1")
    private String direccion1;

    @Column(name = "direccion2", nullable = true)
    private String direccion2;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "dia_ingreso")
    private LocalDate diaDeIngreso;

    @Column(name = "turno")
    private String turno; // Datos para manejar turnos, puede ser de lunes a viernes, diario, etc.

    @Column(name = "celular")
    private String celular;

    @Column(name = "estado")
	int estado;


    // Relación uno a uno con UsuariosModel
    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    private UsuariosModel usuario;


	public Long getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getDireccion1() {
		return direccion1;
	}


	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}


	public String getDireccion2() {
		return direccion2;
	}


	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}


	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}


	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}


	public LocalTime getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public LocalDate getDiaDeIngreso() {
		return diaDeIngreso;
	}


	public void setDiaDeIngreso(LocalDate diaDeIngreso) {
		this.diaDeIngreso = diaDeIngreso;
	}


	public String getTurno() {
		return turno;
	}


	public void setTurno(String turno) {
		this.turno = turno;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public UsuariosModel getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuariosModel usuario) {
		this.usuario = usuario;
	}



    
    
}


