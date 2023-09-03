package com.alura.hotel.dao;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alura.hotel.model.Clientes;

public class ClientesDao {
	
	Connection conect;
	private String query;
	
	
	public ClientesDao(Connection conect) {
		
		this.conect = conect;
		
	}
	
	public void guardar(Clientes cliente) {
		try {
			
			query = "INSERT INTO clientes(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			try(statement) {
				
				statement.setString(1, cliente.getNombre());
				statement.setString(2, cliente.getApellido());
				statement.setDate(3, Date.valueOf(cliente.getFechaNacimiento()));
				statement.setString(4, cliente.getPais());
				statement.setString(5, cliente.getTelefono());
				statement.setBytes(6, asByteArray(cliente.getIdReserva()));
				
				statement.execute();
				
				ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						
						cliente.setId(1);
						
					}
					
				}
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Clientes> listar() {
		
		List<Clientes> resultado = new ArrayList<>();
		
		try {
			
			query = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM clientes";
			
			PreparedStatement statement = conect.prepareStatement(query);
			
			try(statement) {
				
				statement.execute();
				
				ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						
						Integer id = resultSet.getInt("id");
						String nombre = resultSet.getString("nombre");
						String apellido = resultSet.getString("apellido");
						LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
						String nacionalidad = resultSet.getString("nacionalidad");
						String telefono = resultSet.getString("telefono");
						byte[] idBytes = resultSet.getBytes("id_reserva");
			            UUID idReserva = convertBytesToUUID(idBytes);
						
						Clientes fila = new Clientes(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						
						resultado.add(fila);
						
					}
					
				}
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}
	
	public int update(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, String idReserva) {
		
		try {
			
			query = "UPDATE clientes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id = ?  WHERE id_reserva = UNHEX(REPLACE( ? , '-', ''))";
			
			PreparedStatement statement = conect.prepareStatement(query);
			
			try(statement) {
				
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, Date.valueOf(fechaNacimiento));
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id);
				statement.setString(7, idReserva);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public int delete(String idReserva) {
		
		try {
			query = "DELETE clientes, reservas FROM clientes "
			 		+ " JOIN reservas ON  clientes.id_reserva = reservas.id "
			 		+ " WHERE clientes.id_reserva = UNHEX(REPLACE( ? , '-', ''))";
			
			
			PreparedStatement statement = conect.prepareStatement(query);
			
			try(statement) {
				
				statement.setString(1, idReserva);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
				
			}
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	
	 public static byte[] asByteArray(UUID id) {
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

	
	
	
	