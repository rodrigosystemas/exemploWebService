package br.com.kyros.cadastro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Conexao {

	private static final String URL = "jdbc:mysql://localhost/kyros?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String SENHA = "root";
	
	public static Connection conecta() throws SQLException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) { 
			
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(URL, USER, SENHA);
	}
}
