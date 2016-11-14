package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.DbConnection;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddUserDialogController {
	private DbConnection dc; 
	private Connection conn;
	private User user;
	
	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	@FXML
	private PasswordField confirmText;
	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private TextField cityText;
	@FXML
	private TextField streetText;
	@FXML
	private TextField apartmentText;
	@FXML
	private TextField postalCadeText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField emailText;
	@FXML
	private TextField secidText;
	@FXML
	private TextField statusText;
	
	@FXML
	private void handleAddButton() throws SQLException{
		if(nameText.getLength() == 0 ||
				surnameText.getLength() == 0 ||
				usernameText.getLength() == 0 ||
				passwordText.getLength() == 0 ||
				cityText.getLength() == 0 ||
				streetText.getLength() == 0 ||
				apartmentText.getLength() == 0 ||
				postalCadeText.getLength() == 0 ||
				phoneText.getLength() == 0 ||
				emailText.getLength() == 0 ||
				secidText.getLength() == 0 ||
				statusText.getLength() == 0){
			userWarnings("Some brackets are empty","Fill empty brackets");
		} else if(!(passwordText.getText().equals(confirmText.getText()))){
			userWarnings("Invalid password","Password does not match");
		}
		else {
			user = new User(usernameText.getText(),passwordText.getText(),nameText.getText(),surnameText.getText(),
					cityText.getText(),streetText.getText(),apartmentText.getText(),postalCadeText.getText(),
					phoneText.getText(),emailText.getText(),secidText.getText(),statusText.getText());
			showUser();
			if(checkUserInTable()){
				String sql = "insert into tbl_user(username,password,name,surname,city,street,apartment_num,"
						+ "postal_code,phone,email,socsecnumber,status) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setString(1, user.getLogin());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getName());
				pst.setString(4, user.getSurname());
				pst.setString(5, user.getCity());
				pst.setString(6, user.getStreet());
				pst.setString(7, user.getApartment());
				pst.setString(8, user.getPostalCode());
				pst.setString(9, user.getPhone());
				pst.setString(10, user.getEmail());
				pst.setInt(11, Integer.parseInt(user.getPesel()));
				pst.setString(12, user.getStatus());
				
				System.out.println("Sir we made it through psts!");
				pst.executeUpdate();
				
				userSuccess();
				handleCancelButton();
			} else
				userWarnings("Arleady in base","User arelady is in database");
		}
	}
	
	private void showUser(){
		System.out.println(user.getLogin() + ", " +
						   user.getPassword() + ", " +
						   user.getName() + ", " +
						   user.getSurname() + ", " +
						   user.getCity() + ", " +
						   user.getStreet() + ", " +
						   user.getApartment() + ", " +
						   user.getPostalCode() + ", " +
						   user.getPhone() + ", " +
						   user.getEmail() + ", " +
						   user.getPesel() + ", " +
						   user.getStatus());
	}
	
	private boolean checkUserInTable() throws SQLException{
		String queryCheck = "select * from tbl_user where username = '" + user.getLogin() + "' "
				+ "or socsecnumber = '" + user.getPesel() + "'";
		
		dc = new DbConnection();
		conn = dc.connect();
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			checkRs.close();
			checkPst.close();
			return false;
		}
		else{
			checkRs.close();
			checkPst.close();
			return true;
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		Main.dialogStage.close();
	}
	
	private void userWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void userSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("New user created.");
		alert.setContentText("User has been created!");
		alert.showAndWait();
	}
}
