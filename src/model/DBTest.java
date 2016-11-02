package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//1. Get connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib_lite", "root", "Zwierz1993");
			//2. Create statement
			Statement myStmt = myConn.createStatement();
			//3. Execute Query
			ResultSet myRs = myStmt.executeQuery("select * from tbl_admin");
			//4. Process the result test
			while(myRs.next()){
				System.out.println(myRs.getString("login") + "|" + myRs.getString("password"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
