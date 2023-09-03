package com.alura.hotel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reserva {
	
	private UUID id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Double valor;
	private String tipoPago;
	private String tipoHabitacion;
	private List<Clientes> clientes;
	
	

	
	public Reserva(UUID id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String tipoPago, String tipoHabitacion) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.tipoPago = tipoPago;
		this.tipoHabitacion = tipoHabitacion;
	}

	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String tipoPago, String tipoHabitacion) {
		setId();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.tipoPago = tipoPago;
		this.tipoHabitacion = tipoHabitacion;
	}

	public UUID getId() {
		return id;
	}
	
	public String getIdHx() {
		
	       String uuidString = this.id.toString();
	       String shortenedUUID = uuidString.substring(0, 16).toUpperCase();
	        
	        return shortenedUUID;
		
	}
	
	public String getIdHxComplete() {
		
	       String uuidString = this.id.toString().toUpperCase().replace("-", "");
	        
	        return uuidString;
		
	}
	
	public void setId() {
		this.id = UUID.randomUUID();
	}
	
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getTipoPago() {
		return tipoPago;
	}
	
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	
	public void agregar(Clientes clientes) {
		
		if(this.clientes == null) {
			
			this.clientes = new ArrayList<>();
			
		}
		
		this.clientes.add(clientes);
		
	}
	
	public List<Clientes> getClientes() {
		return this.clientes;
	}

	

}
