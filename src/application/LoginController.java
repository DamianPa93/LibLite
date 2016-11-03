package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	private String connection = "jdbc:mysql://localhost:3306/lib_lite";
	private String dbLogin = "root";
	private String dbPass = "Zwierz1993";
	
	@FXML
	private TextField txtLogin; 
	
	@FXML
	private TextField txtPassword; 
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public void LoginCheck(ActionEvent event){
		
		// Make a connections.
		String sql = "Select * from tbl_admin where login=? and cast(password as binary)=?";
		try{
			conn = DriverManager.getConnection(connection, dbLogin, dbPass);
			ps = conn.prepareStatement(sql);
			ps.setString(1, txtLogin.getText());
			ps.setString(2, txtPassword.getText());
			rs = ps.executeQuery();
			
			// Check if login and password are correct.
			if(rs.next()){
				System.out.println("Success");
				Parent test_parent = FXMLLoader.load(getClass().getResource("test.fxml"));
				Scene test_scene = new Scene(test_parent);
				Stage test_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				test_stage.hide();
				test_stage.setScene(test_scene);
				test_stage.show();
			}
			else{
				System.out.println("Username or password are invalid");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
