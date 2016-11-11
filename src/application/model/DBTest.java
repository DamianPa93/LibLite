package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			
		String q =	"select concat(u.name, ' ',u.surname) name, b.title, o.order_date, o.status, o.comments "
				+ "from tbl_order o join tbl_user u on o.user_id = u.id "
				+ "join tbl_book b on o.book_id = b.id "
				+ "where concat(u.name, ' ',u.surname) like '%" + dupa + "%' "
				+ "or b.title like '%" + dupa + "%'";
		
		
			
			System.out.println(q);
			pst = myConn.prepareStatement(q);
			ResultSet myRs = pst.executeQuery(q);
			
			
			while(myRs.next()){
				System.out.println(myRs.getString(1) + "|" + myRs.getString(2));
			} 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
