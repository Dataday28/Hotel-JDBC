package com.alura.hotel.controller;

import java.sql.SQLException;

import com.alura.hotel.dao.LoginDao;
import com.alura.hotel.factory.ConnectionFactory;

public class LoginController {
	
	private LoginDao loginDao;
	
	
	public LoginController() throws SQLException {
		
		this.loginDao = new LoginDao(new ConnectionFactory().creaConexion());
		
	}
	
	public boolean signIn(String userName, String passWord) {
		
		return loginDao.signIn(userName, passWord);
		
	}

}
