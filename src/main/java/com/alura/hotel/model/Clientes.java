package com.alura.hotel.model;

import java.time.LocalDate;
import java.util.UUID;

public class Clientes {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String pais;
	private String telefono;
	private UUID idReserva;
	
	
	
	
	public Clientes(String nombre, String apellido, LocalDate fechaNacimiento, String pais, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
		this.telefono = telefono;
	}



	public Clientes(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String pais, String telefono, UUID idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UUID getIdReserva() {
		return idReserva;
	}
	
	public String getIdHx() {
		
	     String uuidString = this.idReserva.toString();
	     String shortenedUUID = uuidString.substring(0, 16).toUpperCase().replace("-", "");
	        
	     return shortenedUUID;
		
	}
	
	public String getIdHxComplete() {
		
	       String uuidString = this.idReserva.toString().toUpperCase().replace("-", "");
	        
	        return uuidString;
		
	}

	public void setIdReserva(UUID idReserva) {
		this.idReserva = idReserva;
	}
	
	
	
}
