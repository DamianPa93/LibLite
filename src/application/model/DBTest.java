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
			//String dupa = "a";
			
		String q =	"select x.* from ("
				+ "select concat(u.name, ' ',u.surname) borrower, b.title, b.isbn,"
				+ "l.loan_date date_from, DATE_ADD(l.loan_date, INTERVAL 14 DAY) return_to, l.comments "
				+ "from tbl_loan l join tbl_user u on u.id = l.user_id "
				+ "join tbl_book b on b.id = l.book_id ) x "
				+ "where x.borrower like '%st%' "
				+ "or x.title like '%st%' "
				+ "or x.isbn like '%st%'";
		
		
			
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
