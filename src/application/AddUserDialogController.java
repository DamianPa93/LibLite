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
	
	private int loginId;
	
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
		} else if(!statusText.getText().equals("A")&&!statusText.getText().equals("I"))
			userWarnings("Invalid user status","User status:\nA - Active\nI - Inactive");
		else if(nameText.getLength() > 20)
			userWarnings("Too long (" + nameText.getLength() + ")","Value for 'Name' is too long. Max string length is 20");
		else if(surnameText.getLength() > 20)
			userWarnings("Too long (" + surnameText.getLength() + ")","Value for 'Surname' is too long. Max string length is 20");
		else if(usernameText.getLength() > 12)
			userWarnings("Too long (" + usernameText.getLength() + ")","Value for 'Username' is too long. Max string length is 12");
		else if(passwordText.getLength() > 12)
			userWarnings("Too long (" + passwordText.getLength() + ")","Value for 'Password' is too long. Max string length is 12");
		else if(cityText.getLength() > 15)
			userWarnings("Too long (" + cityText.getLength() + ")","Value for 'City' is too long. Max string length is 15");
		else if(streetText.getLength() > 15)
			userWarnings("Too long (" + streetText.getLength() + ")","Value for 'Street' is too long. Max string length is 15");
		else if(apartmentText.getLength() > 5)
			userWarnings("Too long (" + apartmentText.getLength() + ")","Value for 'Apartment' is too long. Max string length is 5");
		else if(postalCadeText.getLength() > 15)
			userWarnings("Too long (" + postalCadeText.getLength() + ")","Value for 'PostalCode' is too long. Max string length is 15");
		else if(phoneText.getLength() > 15)
			userWarnings("Too long (" + phoneText.getLength() + ")","Value for 'Phone' is too long. Max string length is 15");
		else if(emailText.getLength() > 35)
			userWarnings("Too long (" + emailText.getLength() + ")","Value for 'Email' is too long. Max string length is 35");
		else if(secidText.getLength() > 15)
			userWarnings("Too long (" + secidText.getLength() + ")","Value for 'SECID' is too long. Max string length is 15");
		else if(statusText.getLength() > 1)
			userWarnings("Too long (" + statusText.getLength() + ")","Value for 'Status' is too long. Max string length is 1");
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
				//pst.setInt(11, Integer.parseInt(user.getPesel()));
				pst.setString(11, user.getPesel());
				pst.setString(12, user.getStatus());
				
				System.out.println("Sir we made it through psts!");
				pst.executeUpdate();
				
				userSuccess();
				handleCancelButton();
			} else {
				if(loginId == 1)
					userWarnings("Arleady in base","Login arleady in database");
				else
					userWarnings("Arleady in base","SECID arleady in database");
			}		
		}
	}
	
	public boolean isInteger( String input )
	{
	   try
	   {
	      Integer.parseInt( input );
	      return true;
	   }
	   catch( Exception e )
	   {
	      return false;
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
		
		String queryCheck = "select * from tbl_user where username = '" + user.getLogin() + "' ";
		String queryCheck2 = "select * from tbl_user where socsecnumber = '" + user.getPesel() + "'";
		dc = new DbConnection();
		conn = dc.connect();
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		PreparedStatement checkPst2 = conn.prepareStatement(queryCheck2);
		
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		ResultSet checkRs2 = checkPst2.executeQuery(queryCheck2);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			loginId = 1;
			checkRs.close();
			checkPst.close();
			return false;
		} else if(checkRs2.next()){
			loginId = 2;
			checkRs2.close();
			checkPst2.close();
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
