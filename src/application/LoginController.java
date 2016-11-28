package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

	private String connection = "jdbc:mysql://localhost:3306/lib_lite";
	private String dbLogin = "root";
	private String dbPass = "Zwierz1993";
	
	@FXML
	private TextField txtLogin; 
	
	@FXML
	private TextField txtPassword; 

	@FXML
	private void Button() throws IOException{
		if(isCredentialsValid()){
			Main.showMainView();
		} else {
			System.out.println("Fail.");
			credWarning();
		}
	}
	
	private void credWarning(){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.YES, ButtonType.NO);
		alert.setTitle("WARNING");
		alert.setHeaderText("Invalid credenials");
		alert.setContentText("Login or password are invalid");
		Optional<ButtonType> result = alert.showAndWait();
	}
	
	@FXML
	public void onEnter(ActionEvent ae) throws IOException{
		System.out.println("Enter pressed");
		Button();
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
