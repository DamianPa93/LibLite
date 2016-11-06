package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String dupa = "a";
			String sql = "select concat(name, ' ',surname) as name, birth_date, country, comments "
					+ "from tbl_author where concat(name, ' ',surname) like '%" + dupa + "%'";
			
			pst = myConn.prepareStatement(sql);
			//pst.setString(1, "a");
			//ResultSet myRs = myStmt.executeQuery(sql);
			ResultSet myRs = pst.executeQuery(sql);
			//System.out.println("ok");
			//4. Process the result test
			while(myRs.next()){
				System.out.println(myRs.getString(1) + "|" + myRs.getString(2));
			} 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
