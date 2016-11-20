package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//1. Get connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib_lite", "root", "Zwierz1993");
			//2. Create statement
			//Statement myStmt = myConn.createStatement(); 
			PreparedStatement pst = null;
			//3. Execute Query
			//String dupa = "a";
			
			
			String sql = "delete from tbl_admin where id = 4";
		
			pst = myConn.prepareStatement(sql);
			
			pst.executeUpdate();
			
			System.out.println(sql);
			
			//System.out.println(q);
			//pst = myConn.prepareStatement(q);
			//ResultSet myRs = pst.executeUpdate();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
