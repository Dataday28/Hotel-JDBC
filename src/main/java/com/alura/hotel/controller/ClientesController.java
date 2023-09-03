package com.alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.alura.hotel.dao.ClientesDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Clientes;

public class ClientesController {
	
	private ClientesDao clientesDao;
	
	
	public ClientesController() {
		
		this.clientesDao = new ClientesDao(new ConnectionFactory().creaConexion());
		
	}
	
	public void save(Clientes cliente, UUID idReserva) {
		
		cliente.setIdReserva(idReserva);
		clientesDao.guardar(cliente);
		
	}
	
	public List<Clientes> listar() {
		
		return clientesDao.listar();
		
	}
	
	public int update(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, String idReserva) {
		
		return clientesDao.update(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
		
	}
	
	public int delete(String idReserva) {
		
		return clientesDao.delete(idReserva);
		
	}

}
