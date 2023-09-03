package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	Connection conect;
	
	
	public LoginDao(Connection conect) {
		
		this.conect = conect;
		
	}
	
	public boolean signIn(String userName, String passWord) {
		
		try {
			
			String query = "SELECT * FROM login WHERE usuario = ? AND contrasena = ?";
			
			PreparedStatement statement = conect.prepareStatement(query);
			
			try(statement){
			
				statement.setString(1, userName);
				statement.setString(2, passWord);
				
				ResultSet resultSet = statement.executeQuery();
				
				
				return resultSet.next();
				
			}
				
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}

}
