package com.alura.hotel.test;

import java.time.LocalDate;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.model.Reserva;

public class PruebaReservaDao {
	
	public static void main(String[] args) {
		
		LocalDate fecha1 = LocalDate.of(2023, 12, 1);
		LocalDate fecha2 = LocalDate.of(2024, 1, 5);
		Double valor = 680.0;
		String pago = "Trajeta de debito";
		String habitacion = "Suit";
		
		
		Reserva reserva = new Reserva(fecha1, fecha2, valor, pago, habitacion);
		
		
		ReservaController reservaController = new ReservaController();
		reservaController.save(reserva);
			
	}
}
