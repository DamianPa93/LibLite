package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.DbConnection;
import application.model.Publisher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class EditPublisherDialogController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private Publisher publisher;
	
	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private TextField organizationText;
	@FXML
	private TextField countryText;
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	
	public EditPublisherDialogController(Publisher publisher){
		this.publisher = publisher;
	}
	
	@FXML
	private void handleSaveButton() throws SQLException{
		if(nameText.getLength() == 0 && surnameText.getLength() == 0 && organizationText.getLength() == 0){
			publisherWarnings("Null values","Insert name value at least into one bracket"); 
		} else {
			if(checkPublisherInTable()){
			
			System.out.println(nameText.getText() + "|" + 
					surnameText.getText() + "|" +
					organizationText.getText() + "|" +
					countryText.getText() + "|" + 
					publisher.getId());
			
			
				String sql = "update tbl_publisher "
						+ "set name = ?, "
						+ "second_name = ?, "
						+ "organization = ?, "
						+ "country = ? "
						+ " where id = ? ;";
				System.out.println(sql);
					
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setString(1, nameText.getText());
				pst.setString(2, surnameText.getText());
				pst.setString(3, organizationText.getText());
				pst.setString(4, countryText.getText());
				pst.setInt(5, publisher.getId());
				
				pst.executeUpdate();
				
				publisherSuccess(); 
				handleCancelButton();
			}
			else 
				publisherWarnings("Arleady in base","Publisher arelady is in database");
		}
	}
	
	
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	private void showPublisher(){
		System.out.println(publisher.getName() + ", " +
				publisher.getSurname() + ", " +
				publisher.getOrganization() + ", " +
				publisher.getCountry() + ", " +
				publisher.getId());
	}
	
	
	private boolean checkPublisherInTable() throws SQLException{	
		String queryCheck = "select * from tbl_publisher where "
				+ "name = '" + nameText.getText() + "' and second_name = '" + surnameText.getText() +"' "
						+ "and organization = '" + organizationText.getText() + "'";
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			checkRs.close();
			checkPst.close();
			System.out.println("Publisher in database");
			return false;
		}
		else{
			checkRs.close();
			checkPst.close();
			System.out.println("Publisher NOT in database");
			return true;
		}
	} 
	
	private void publisherWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void publisherSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("Publisher altered");
		alert.setContentText("Publisher successful updated!");
		alert.showAndWait();
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameText.setText(publisher.getName());
		surnameText.setText(publisher.getSurname());
		organizationText.setText(publisher.getOrganization());
		countryText.setText(publisher.getCountry());
		
		dc = new DbConnection();
		conn = dc.connect();
		
		buttonSave.setOnAction((event) -> {
			System.out.println("event");
			showPublisher();
			try {
				System.out.println(publisher.getId());
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
