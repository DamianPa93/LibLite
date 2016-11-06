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
			String dupa = "har";
			
		String q =	"select b.isbn, concat(a.name, ' ', a.surname) as author, b.title, " +
			   "group_concat(c.category separator ', ') as category, " +
		       "concat(p.name, ' ', p.second_name, ' ', p.organization) as publisher, " +
		       "b.date_of_publication, b.book_rating, b.comments, o.id, o.order_date, " +
		       "l.loan_date " +
		"from tbl_book b join tbl_author a on b.id_author = a.id " +
		"join tbl_category c on (b.id_category_1 = c.id " +
								 "OR b.id_category_2 = c.id " +
								 "OR b.id_category_3 = c.id) " +
		"left join tbl_publisher p on b.id_publisher = p.id " +
		"left join tbl_order o on b.id = o.book_id " +
		"left join tbl_loan l on b.id = l.book_id " +
		"where "   +
		"(category like '%" + dupa + "%' or b.isbn like '%" + dupa + "%' or concat(a.name, ' ', a.surname) like '%" + dupa + "%' " +
		 "or b.title like '%" + dupa + "%' or concat(p.name, ' ', p.second_name, ' ', p.organization) like '%" + dupa + "%' " +
		 "or b.book_rating like '%" + dupa + "%') group by 1,2,3,5,9,11";
		
		
			
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
