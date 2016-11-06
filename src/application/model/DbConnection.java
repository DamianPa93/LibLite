package application.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	public Connection connect(){
		try{
			String url = "jdbc:mysql://localhost:3306/lib_lite";
			String user = "root";
			String password = "Zwierz1993";
			
			Connection conn = DriverManager.getConnection(url,user,password);
			return conn;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
