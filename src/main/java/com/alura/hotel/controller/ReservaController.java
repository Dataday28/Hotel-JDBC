package com.alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import com.alura.hotel.dao.ReservaDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Reserva;

public class ReservaController {
	
	private ReservaDao reservaDao;
	
	
	public ReservaController() {
		
		this.reservaDao = new ReservaDao(new ConnectionFactory().creaConexion());
		
	}
	
	public void save(Reserva reserva) {
		
		reservaDao.save(reserva);
		
	}
	
	public List<Reserva> listar() {
		
		return reservaDao.listar();
		
	}
	
	public List<Reserva> buscar(String idR, String apellido) {
		
		return reservaDao.buscar(idR, apellido);
		
	}
	
	public int update(String id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaPago, String tipoHabitacion) {
		
		return reservaDao.update(id, fechaEntrada, fechaSalida, valor, formaPago, tipoHabitacion);
		
	}
	
	public int delete(String id) {
		
		return reservaDao.delete(id);
		
	}

}
