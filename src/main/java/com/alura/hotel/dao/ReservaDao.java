package com.alura.hotel.dao;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alura.hotel.model.Clientes;
import com.alura.hotel.model.Reserva;

public class ReservaDao {
	
	Connection conect;
	private String query;
	
	
	public ReservaDao(Connection conect) {
		
		this.conect = conect;
		
	}
	
	public void save(Reserva reserva) {
		
		try {
			
			query = "INSERT INTO reservas(id, fecha_entrada, fecha_salida, valor, forma_pago, tipo_habitacion)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conect.prepareStatement(query);
			
			try(statement) {
							
				statement.setBytes(1, asByteArray(reserva.getId()));
				statement.setDate(2, Date.valueOf(reserva.getFechaEntrada()));
				statement.setDate(3, Date.valueOf(reserva.getFechaSalida()));
				statement.setDouble(4, reserva.getValor());
				statement.setString(5, reserva.getTipoPago());
				statement.setString(6, reserva.getTipoHabitacion());
				
				statement.execute();
			}			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	 
	 public List<Reserva> listar() {
		 
		 List<Reserva> resultado = new ArrayList<>();
		 
		 try {
			 
			 query = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, tipo_habitacion FROM reservas";
			 
			 PreparedStatement statement = conect.prepareStatement(query);
			 
			 try(statement) {
				 
				 statement.execute();
				 
				 ResultSet resultSet = statement.getResultSet();
				 
				 try(resultSet) {
					 
					 while(resultSet.next() ) {
						 
						 byte[] idBytes = resultSet.getBytes("id");
			             UUID id = convertBytesToUUID(idBytes);
						 LocalDate fechaEntrada = resultSet.getDate("fecha_entrada").toLocalDate();
						 LocalDate fechaSalida = resultSet.getDate("fecha_salida").toLocalDate();
						 double valor = resultSet.getDouble("valor");
						 String formaPago = resultSet.getString("forma_pago");
						 String tipoHabitacion = resultSet.getString("tipo_habitacion");
						 
						 Reserva fila = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago, tipoHabitacion);
						 resultado.add(fila);
						 
						 
					 }
					 
				 }
				 
			 }
			 
		 }catch(SQLException e) {
			 throw new RuntimeException(e);
		 }
		 
		 return resultado;
		 
	 }
	 
	 public List<Reserva> buscar(String idR, String apellido) {
		 
		 List<Reserva> resultado = new ArrayList<>();
		 
		 try {
			 
			 query = "SELECT r.id, r.fecha_entrada, r.fecha_salida, valor, r.forma_pago, r.tipo_habitacion "
			 		+ " c.id, c.nombre, c.apellido, c.fecha_nacimiento, c.nacionalidad, c.telefono, c.id_reserva "
			 		+ " FROM reservas r INNER JOIN clientes c ON r.id = c.id_reserva "
			 		+ " WHERE c.apellido = ? OR HEX(r.id) LIKE ?;";
			 
			 PreparedStatement statement = conect.prepareStatement(query);
			 
			 try(statement) {
				 
				 statement.setString(1, apellido);
				 statement.setString(2, idR + "%");
				 statement.execute();
				 
				 ResultSet resultSet = statement.getResultSet();
				 
				 try(resultSet) {
					 
					 while(resultSet.next() ) {
						 
						 byte[] idBytes = resultSet.getBytes("r.id");
			             UUID id = convertBytesToUUID(idBytes);
						 LocalDate fechaEntrada = resultSet.getDate("r.fecha_entrada").toLocalDate();
						 LocalDate fechaSalida = resultSet.getDate("r.fecha_salida").toLocalDate();
						 double valor = resultSet.getDouble("r.valor");
						 String formaPago = resultSet.getString("r.forma_pago");
						 String tipoHabitacion = resultSet.getString("r.tipo_habitacion");
						 
						 var reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago, tipoHabitacion);
						 resultado.add(reserva);
						 
						 
						Integer idClientes = resultSet.getInt("c.id");
						String nombre = resultSet.getString("c.nombre");
						String getApellido = resultSet.getString("c.apellido");
						LocalDate fechaNacimiento = resultSet.getDate("c.fecha_nacimiento").toLocalDate();
						String nacionalidad = resultSet.getString("c.nacionalidad");
						String telefono = resultSet.getString("c.telefono");
						byte[] idBytes2 = resultSet.getBytes("c.id_reserva");
				        UUID idReserva = convertBytesToUUID(idBytes2);
						 
						Clientes cliente = new Clientes(idClientes, nombre, getApellido, fechaNacimiento,nacionalidad, telefono, idReserva);
						reserva.agregar(cliente); 
						 
						 
					 }
					 
				 }
				 
			 }
			 
		 }catch(SQLException e) {
			 throw new RuntimeException(e);
		 }
		 
		 return resultado;
		 
	 }
	 
	 public int update(String id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaPago, String tipoHabitacion) {
		 
		 try {
			 
			 query = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ?, tipo_habitacion = ? WHERE id = UNHEX(REPLACE( ? , '-', ''))";
			 
			/* String query2 = "UPDATE reservas r INNER JOIN clientes c ON r.id = c.id_reserva  SET r.fecha_entrada = ?, r.fecha_salida = ?', r.valor = ?, r.forma_pago = ?, "
			 		+ " c.nombre = ?, c.apellido = ?, c.fecha_nacimiento = ?, c.nacionalidad = ?, c.telefono = ? "
			 		+ "WHERE r.id = UNHEX(REPLACE( ? , '-', ''))";*/
			 
			 PreparedStatement statement = conect.prepareStatement(query);
			 
			 try(statement) {
				 
				 statement.setDate(1, Date.valueOf(fechaEntrada));
				 statement.setDate(2, Date.valueOf(fechaSalida));
				 statement.setDouble(3, valor);
				 statement.setString(4, formaPago);
				 statement.setString(5, tipoHabitacion);
				 statement.setString(6, id);
				/* statement.setString(5, nombre);
				 statement.setString(6, apellido);
				 statement.setDate(7, Date.valueOf(fechaNacimiento));
				 statement.setString(8, nacionalidad);
				 statement.setString(9, telefono);
				 statement.setString(10, id);*/
				 
				 statement.execute();
				 
				 int updateCount = statement.getUpdateCount();
				 
				 return updateCount;
				 
			 }
			 
		 }catch(SQLException e) {
			 throw new RuntimeException(e);
		 }
		 
	 }
	 
	 public int delete(String id) {
		 try {
			 
			 query = "DELETE clientes, reservas FROM clientes "
			 		+ " JOIN reservas ON  clientes.id_reserva = reservas.id "
			 		+ " WHERE clientes.id_reserva = UNHEX(REPLACE( ? , '-', ''))";
			 
			 PreparedStatement statement = conect.prepareStatement(query);
			 
			 try(statement) {
				 
				 statement.setString(1, id);
				 
				 statement.execute();
				 
				 int updateCount = statement.getUpdateCount();
				 
				 return updateCount;
				 
			 }
		
		 }catch(SQLException e) {
			 
			 throw new RuntimeException(e);
			 
		 }
		 
	 }
	 
	 private static byte[] asByteArray(UUID id) {
	        long msb = id.getMostSignificantBits();
	        long lsb = id.getLeastSignificantBits();
	        byte[] buffer = new byte[16];
	        for (int i = 0; i < 8; i++) {
	            buffer[i] = (byte) (msb >>> 8 * (7 - i));
	            buffer[i + 8] = (byte) (lsb >>> 8 * (7 - i));
	        }
	        return buffer;
	    }
	 
	 private UUID convertBytesToUUID(byte[] bytes) {
		    ByteBuffer buffer = ByteBuffer.wrap(bytes);
		    long mostSigBits = buffer.getLong();
		    long leastSigBits = buffer.getLong();
		    return new UUID(mostSigBits, leastSigBits);
		}
	

}
