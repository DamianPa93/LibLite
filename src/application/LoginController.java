package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

	private String connection = "jdbc:mysql://localhost:3306/lib_lite";
	private String dbLogin = "root";
	private String dbPass = "Zwierz1993";
	private Main main;
	
	@FXML
	private TextField txtLogin; 
	
	@FXML
	private TextField txtPassword; 

	@FXML
	private void Button() throws IOException{
		if(isCredentialsValid()){
			main.showMainView();
		} else {
			System.out.println("Fail.");
		}
	}
	
	private boolean isCredentialsValid(){
		boolean in = false;
		String sql = "Select * from tbl_admin where login=? and cast(password as binary)=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			conn = DriverManager.getConnection(connection, dbLogin, dbPass);
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, txtLogin.getText());
			pst.setString(2, txtPassword.getText());
			
			rs = pst.executeQuery();
					
			while(rs.next()){
				in = true;
			}
			rs.close();
			pst.close();
			conn.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return in;
	}
}
