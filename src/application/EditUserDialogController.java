package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.DbConnection;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class EditUserDialogController implements Initializable{
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
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	
	public EditUserDialogController(User user){
		this.user = user;
	}
	
	@FXML
	private void handleSaveButton() throws SQLException{
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
		} else if(!(passwordText.getText().equals(confirmText.getText())))
			userWarnings("Invalid password","Password does not match");
		else {
			if(checkUserInTable()){
				String sql = "update tbl_user set username = ? ,"
						+ "password = ? ,"
						+ "name = ? ,"
						+ "surname = ? ,"
						+ "city = ? ,"
						+ "street = ? ,"
						+ "apartment_num = ? ,"
						+ "postal_code = ? ,"
						+ "phone = ? ,"
						+ "email = ? ,"
						+ "socsecnumber = ? ,"
						+ "status = ? "
						+ "where id = " + user.getId();	
					
				System.out.println("DONE SO FAR");
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setString(1, usernameText.getText());
				pst.setString(2, passwordText.getText());
				pst.setString(3, nameText.getText());
				pst.setString(4, surnameText.getText());
				pst.setString(5, cityText.getText());
				pst.setString(6, streetText.getText());
				pst.setString(7, apartmentText.getText());
				pst.setString(8, postalCadeText.getText());
				pst.setString(9, phoneText.getText());
				pst.setString(10, emailText.getText());
				pst.setInt(11, Integer.parseInt(secidText.getText()));
				pst.setString(12, statusText.getText());
				
				pst.executeUpdate();
				
				userSuccess(); 
				handleCancelButton();
					
			} else
				userWarnings("Arleady in base","User login or SECID arelady is in database");
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	private boolean checkUserInTable() throws SQLException{
		String queryCheck = "select * from tbl_user where username = '" + usernameText.getText() + "' "
				+ "or socsecnumber = '" + secidText.getText() + "'";
		
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
		alert.setHeaderText("User altered");
		alert.setContentText("User successful updated!");
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameText.setText(user.getName());
		surnameText.setText(user.getSurname());
		usernameText.setText(user.getLogin());
		passwordText.setText(user.getPassword());
		confirmText.setText(user.getPassword());
		cityText.setText(user.getCity());
		streetText.setText(user.getStreet());
		apartmentText.setText(user.getApartment());
		postalCadeText.setText(user.getPostalCode());
		phoneText.setText(user.getPhone());
		emailText.setText(user.getEmail());
		secidText.setText(String.valueOf(user.getSecId()));
		statusText.setText(user.getStatus()); 
		
		dc = new DbConnection();
		conn = dc.connect();
		
		buttonSave.setOnAction((event) -> {
			System.out.println("event");
			try {
				System.out.println(user.getId());
				handleSaveButton();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		});
		
		buttonCancel.setOnAction((event) -> {
			handleCancelButton();
		});
	}
}
